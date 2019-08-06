package practice.jvm.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象晋升到老年代的过程
 *
 * -Xmx1024M -Xms1024M -XX:+PrintGCDetails -XX:MaxTenuringThreshold=15 -XX:+PrintHeapAtGC
 *
 * @title MaxTenuringThreshold
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-02
 **/
public class MaxTenuringThreshold {

    public static final int _1M = 1024 * 1024;

    public static final int _1K = 1024;

    public static void main(String[] args) {
        Map<Integer, byte[]> map = new HashMap<>();
        // 创建了1K的字节数组，并且map持有了它的引用，所以不会被gc
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
            map.put(i, b);
        }

        // 这里疯狂分配空间，势必会触发n波gc，然后上面的map会在每一波都存活下来，最后晋升到老年代
        for (int k = 0; k < 17; k++) {
            for (int i = 0; i < 270; i++) {
                byte[] g = new byte[_1M];
            }
        }
    }

}
