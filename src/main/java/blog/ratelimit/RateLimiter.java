package blog.ratelimit;

/**
 * RateLimit
 *
 * @title RateLimit
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/4
 **/
public interface RateLimiter {

    /**
     * 限流，如果通过，则返回true；如果被限流，则返回false
     */
    boolean tryAcquire();

}
