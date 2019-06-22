package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CacheHealthChecker
 *
 * @title CacheHealthChecker
 * @Description
 * @Author donglongcheng01
 * @Date 2019-02-20
 **/
public class CacheHealthChecker extends BaseHealthChecker {


    public CacheHealthChecker(CountDownLatch latch) {
        super(latch, "Cache Service");
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is UP");
    }
}
