package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * DatabaseHealthChecker
 *
 * @title DatabaseHealthChecker
 * @Description
 * @Author donglongcheng01
 * @Date 2019-02-20
 **/
public class DatabaseHealthChecker extends BaseHealthChecker {


    public DatabaseHealthChecker(CountDownLatch latch) {
        super(latch, "Database Service");
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is UP");
    }
}
