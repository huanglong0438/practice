package practice.jvm.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * 大对象进入老年代，每次5k，一直分配到5m，然后通过map来持有索引
 * 1. 所有对象都分配到新生代
 * -Xmx32m -Xms32m -XX:+UseSerialGC -XX:+PrintGCDetails
 * 2. 设置老年代的门槛，强行搞到老年代（但是由于默认的TLAB参数，所以还是在新生代）
 * -Xmx32m -Xms32m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000
 * 3. 在2的基础上，禁用TLAB
 * -Xms32m -Xms32m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:-UseTLAB -XX:PretenureSizeThreshold=1000
 *
 * @title PretenureSizeThreShold
 * @Description 大对象进入老年代
 * @Author donglongcheng01
 * @Date 2019-08-05
 **/
public class PretenureSizeThreShold {

    public static final int _1K = 1024;

    public static void main(String[] args) {
        Map<Integer, byte[]> map = new HashMap<>();
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
            map.put(i, b);
        }
    }

}
