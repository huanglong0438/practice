package practice.jvm;


import java.util.HashMap;
import java.util.Map;

/**
 * StopWorldTest
 * 把年轻代设置的超小，这样大对象会直接进入老年代，触发Full GC
 * -Xmx1g -Xms1g -Xmn512k -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails
 *
 * @title StopWorldTest
 * @Description
 * @Author donglongcheng01
 * @Date 2019-07-10
 **/
public class StopWorldTest {

    public static class MyThread extends Thread {

        Map<Long, byte[]> map = new HashMap<>();

        @Override
        public void run() {
            try {
                while (true) {
                    if (map.size() * 512 / 1024 / 1024 >= 900) {
                        map.clear();
                        System.out.println("clean map");
                    }
                    byte[] b1;
                    for (int i = 0; i < 1000; i++) {
                        // put 100个512字节的数据 = 51k，太小了，minorgc还是会发生；改成512k，这样就不得不进老年代了
                        b1 = new byte[512];
                        map.put(System.nanoTime(), b1);
                    }
                    Thread.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class PrintThread extends Thread {
        public static final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    // 休眠计时法，100 200 300 ... 600 800 意味着中间发生了一次STW
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        PrintThread p = new PrintThread();
        t.start();
        p.start();
    }

}
