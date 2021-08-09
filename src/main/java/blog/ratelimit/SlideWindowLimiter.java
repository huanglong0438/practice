package blog.ratelimit;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Map;

/**
 * SlideWindow
 *
 * @title SlideWindow
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/4
 **/
public class SlideWindowLimiter implements RateLimiter {

    /**
     * 每分钟限制的请求数
     */
    private final long permitsPerMinute;

    /**
     * 当前小窗口的描述 -> 当前小窗口的计数
     */
    private final Map<Long, Integer> counters;

    public SlideWindowLimiter(long permitsPerMinute, Map<Long, Integer> counters) {
        this.permitsPerMinute = permitsPerMinute;
        this.counters = counters;
    }

    @Override
    public synchronized boolean tryAcquire() {
        // 当前是第几个十秒
        long currentWindowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / 10 * 10;
        int currentWindowCount = getCurrentWindowCount(currentWindowTime); // 获得其对应的count
        if (currentWindowCount >= permitsPerMinute) {
            return false;
        }
        counters.merge(currentWindowTime, 1, Integer::sum);
        return true;
    }

    private int getCurrentWindowCount(long currentWindowTime) {
        long startTime = currentWindowTime - 50;
        int result = 0;
        Iterator<Map.Entry<Long, Integer>> iterator = counters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = iterator.next();
            if (entry.getKey() < startTime) {
                iterator.remove();
            } else {
                result += entry.getValue();
            }
        }
        return result;
    }

}
