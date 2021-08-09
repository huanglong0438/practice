package blog.ratelimit;

/**
 * FirmWindow
 *
 * @title FirmWindow
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/4
 **/
public class CounterRateLimiter implements RateLimiter {

    /**
     * 每秒限制的请求数
     */
    private final long permitsPerSecond;

    /**
     * 窗口的开始时间
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 窗口内的请求数计数
     */
    private int counter;

    public CounterRateLimiter(long permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - timestamp < 1000) {
            // 没有超过1s
            if (counter < permitsPerSecond) {
                counter++;
                return true;
            } else {
                return false;
            }
        } else {
            // 距离上个窗口的开始时间已经超过1s，重置
            counter = 0;
            timestamp = now;
            return true;
        }
    }

}
