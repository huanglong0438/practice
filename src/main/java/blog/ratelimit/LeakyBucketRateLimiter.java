package blog.ratelimit;

/**
 * LeakyBucketRateLimiter
 *
 * @title LeakyBucketRateLimiter
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/5
 **/
public class LeakyBucketRateLimiter implements RateLimiter {

    private final int capacity;

    private final int permitsPerSecond;

    private long remainedWater;

    private long timeStamp = System.currentTimeMillis();

    public LeakyBucketRateLimiter(int capacity, int permitsPerSecond) {
        this.capacity = capacity;
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        long timeGap = (now - timeStamp) / 1000; // 距离上次请求（注水）过去了xx秒
        remainedWater = Math.max(0, remainedWater - timeGap * permitsPerSecond); // 过去了xx秒期间漏掉了多少水，剩下了多少水
        timeStamp = now;
        if (remainedWater < capacity) {
            remainedWater += 1; // 注水
            return true;
        }
        return false;
    }
}
