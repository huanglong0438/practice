package practice.jvm.ref;

import java.lang.ref.SoftReference;

/**
 * 软引用，内存资源紧张的时候，软引用对象会被回收，所以软引用对象不会引起内存溢出
 * -Xmx10m
 *
 * @title SoftRef
 * @Description 软引用
 * @Author donglongcheng01
 * @Date 2019-07-10
 **/
public class SoftRef {
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

    public static void main(String[] args) {
        User u = new User(1, "geym");
        SoftReference<User> userSoftRef = new SoftReference<>(u);
        u = null;

        System.out.println(userSoftRef.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftRef.get());

        byte[] b = new byte[1024 * 925 * 7];
        System.gc();
        System.out.println(userSoftRef.get());
    }

}
