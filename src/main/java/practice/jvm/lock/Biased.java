package practice.jvm.lock;

import java.util.List;
import java.util.Vector;

/**
 * <pre>
 * 偏向锁对于性能的优化测试
 * 疯狂往Vector里add，由于Vector是同步的，所以会一直加锁
 *
 * JVM开启偏向锁：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
 * JVM关闭偏向锁：-XX:-UseBiasedLocking -client -Xmx512m -Xms512m
 *
 * 开启偏向锁，执行耗时375；关闭偏向锁耗时622
 * </pre>
 *
 * @title Biased
 * @Description 偏向锁对于性能的优化测试
 * @Author donglongcheng01
 * @Date 2019-08-20
 **/
public class Biased {

    public static List<Integer> numberList = new Vector<>();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}
