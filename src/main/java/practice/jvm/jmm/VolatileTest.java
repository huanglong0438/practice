package practice.jvm.jmm;

/**
 * VolatileTest
 *
 * @title VolatileTest
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-21
 **/
public class VolatileTest {

    public static class MyThread extends Thread {

//        private volatile boolean stop = false;
        private volatile boolean stop = false;

        public void stopMe() {
            stop = true;
        }

        @Override
        public void run() {
            int i = 0;
            while (!stop) {
                i++;
            }
            System.out.println("Stop Thread");
        }
    }

    public static void main(String[] args) throws Exception {
        MyThread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.stopMe();
        Thread.sleep(1000);
    }


}
