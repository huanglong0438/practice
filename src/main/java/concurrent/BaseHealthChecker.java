package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * BaseHealthChecker
 *
 * @title BaseHealthChecker
 * @Description
 * @Author donglongcheng01
 * @Date 2019-02-20
 **/
public abstract class BaseHealthChecker implements Runnable {

    private CountDownLatch latch;

    private String serviceName;

    private boolean serviceUp;

    public BaseHealthChecker(CountDownLatch latch, String serviceName) {
        this.latch = latch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            serviceUp = false;
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    public abstract void verifyService();
}
