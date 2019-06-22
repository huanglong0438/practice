package practice.synchronizedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by donglongcheng01 on 2018/7/5.
 */
public class RunnableTest implements Runnable {

    private synchronized  void testSyncMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getId() + "-testSyncMethod:" + i);
        }
    }

    public void run() {
        testSyncMethod();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        RunnableTest rt = new RunnableTest();
        RunnableTest rt1 = new RunnableTest();
        exec.execute(rt);
        exec.execute(rt1);
        exec.shutdown();
    }

}
