package practice.synchronizedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by donglongcheng01 on 2018/7/5.
 */
public class RunnableTest2 implements Runnable {

    private void testSyncBlock() {
        synchronized (RunnableTest.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getId()+"testSyncBlock:" + i);
            }
        }
    }
    public void run() {
        testSyncBlock();
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        RunnableTest2 rt = new RunnableTest2();
        RunnableTest2 rt1 = new RunnableTest2();
        exec.execute(rt);
        exec.execute(rt1);
        exec.shutdown();
    }

}
