package practice.jvm.gc;

/**
 * <pre>
 * TLAB(Thread Local Allocation Buffer，相当于JVM版的ThreadLocal)性能对比
 * 1. 开启TLAB，并且关闭可能影响速度的后台编译、逃逸分析等
 * -XX:+UseTLAB -Xcomp -XX:-BackgroundCompilation -XX:-DoEscapeAnalysis -server
 * 2. 关闭TLAB
 * -XX:-UseTLAB -Xcomp -XX:-BackgroundCompilation -XX:-DoEscapeAnalysis -server
 * 3. 查看TLAB日志，手动指定TLAB大小
 * -XX:+UseTLAB -XX:+PrintTLAB -XX:+PrintGC -XX:TLABSize=102400 -XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis -server
 *
 * </pre>
 *
 * @title UseTLAB
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-05
 **/
public class UseTLAB {

    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }

}
