package practice.jvm;

/**
 * 新生代的配置
 * 1. 新生代分配较小，每次都直接到老年代 -Xmx20m -Xms20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * 2. 新生代扩大，触发minorGC -Xmx20m -Xms20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * 3. 新生代足够大，不会发生GC -Xmx20m -Xms20m -Xmn15m -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 * @title NewSizeDemo
 * @Description 新生代的配置
 * @Author donglongcheng01
 * @Date 2019-07-08
 **/
public class NewSizeDemo {

    public static void main(String[] args) {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }

}
