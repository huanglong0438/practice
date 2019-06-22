package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * NetworkHealthChecker
 *
 * @title NetworkHealthChecker
 * @Description
 * @Author donglongcheng01
 * @Date 2019-02-20
 **/
public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker(CountDownLatch latch) {
        super(latch, "Network Service");
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is UP");
    }
}
