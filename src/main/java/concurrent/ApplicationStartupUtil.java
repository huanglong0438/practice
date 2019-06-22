package concurrent;

import mycollections.mylists.ArrayList;
import mycollections.mylists.List;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ApplicationStartupUtil
 *
 * @title ApplicationStartupUtil
 * @Description
 * @Author donglongcheng01
 * @Date 2019-02-20
 **/
public class ApplicationStartupUtil {

    private static List<BaseHealthChecker> services;

    private static CountDownLatch latch;

    // 如果将executor定义成静态的，那么就固定了是三个，每次调用会复用这个线程池，不需要shutdown
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public ApplicationStartupUtil() {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception {
        latch = new CountDownLatch(3);

        services = new ArrayList<>();
        services.add(new NetworkHealthChecker(latch));
        services.add(new CacheHealthChecker(latch));
        services.add(new DatabaseHealthChecker(latch));

//        Executor写到这里面，可以shutdown，但是每次执行方法都新建线程，线程池就没意义了
//        Executor executor = Executors.newFixedThreadPool(3);

        for (BaseHealthChecker checker : services) {
            executor.execute(checker);
        }

        System.out.println("awaiting...");
        latch.await();
        System.out.println("all done");

//        如果是本地建的线程池，这里要shutdown
//        ((ExecutorService) executor).shutdown();

        for (BaseHealthChecker checker : services) {
            if (!checker.isServiceUp()) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        try {
            boolean ready = checkExternalServices();
            if (ready) {
                System.out.println("ojbk");
            } else {
                System.out.println("not ojbk");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
