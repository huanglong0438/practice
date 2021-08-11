package blog.ratelimit;

/**
 * TokenBucketRateLimiter
 *
 * @title TokenBucketRateLimiter
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/10
 **/
public class TokenBucketRateLimiter implements RateLimiter {

    private final long capacity;

    private final long generatedPerSeconds;

    private long lastTokenTime = System.currentTimeMillis();

    private long currentTokens;

    public TokenBucketRateLimiter(long capacity, long generatedPerSeconds) {
        this.capacity = capacity;
        this.generatedPerSeconds = generatedPerSeconds;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (needSync(now)) {
            // 更新令牌桶的信息（令牌数、最近更新时间）
            long newPermits = (now - lastTokenTime) / 1000 * generatedPerSeconds;
            currentTokens = Math.min(currentTokens + newPermits, capacity);
            lastTokenTime = now;
        }
        if (currentTokens > 0) {
            currentTokens--;
            return true;
        }
        return false;
    }

    /**
     * 距离上次更新已经超过了1s，需要重新同步令牌桶的信息
     */
    private boolean needSync(long now) {
        return now - lastTokenTime >= 1000;
    }
}
