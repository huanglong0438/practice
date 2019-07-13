package practice.jvm.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * SoftRefQ
 * 给软引用构造方法中传一个队列ReferenceQueue，就可以从队列中获取那些被回收掉的对象，该队列用户监控软引用的回收情况
 *
 * @title SoftRefQ
 * @Description
 * @Author donglongcheng01
 * @Date 2019-07-10
 **/
public class SoftRefQ {
    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name=" + name +
                    '}';
        }
    }

    static ReferenceQueue<User> softQueue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (softQueue != null) {
                    UserSoftReference obj = null;
                    try {
                        obj = (UserSoftReference) softQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (obj != null) {
                        System.out.println("userid " + obj.uid + " is delete");
                    }
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User> {

        int uid;

        public UserSoftReference(User reference, ReferenceQueue<? super User> q) {
            // SoftReference的构造方法，要么只传强引用；要么传强引用+队列
            super(reference, q);
            uid = reference.id;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1, "geym");
        softQueue = new ReferenceQueue<>();
        UserSoftReference userSoftReference = new UserSoftReference(u, softQueue);
        u = null;
        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftReference.get());

        System.out.println("try to create byte array and GC");
        byte[] b = new byte[1024 * 925 * 7];
        System.gc();
        System.out.println(userSoftReference.get());

        Thread.sleep(1000);
    }

}
