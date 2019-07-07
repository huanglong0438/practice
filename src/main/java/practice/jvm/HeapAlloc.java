package practice.jvm;

/**
 * JVM栈空间分配
 * -Xmx20m -Xms5m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 * 使用以上
 *
 * @Author: donglongcheng
 * @Description: JVM栈空间分配
 * @Date: Create in 0:17 2019/7/8
 */
public class HeapAlloc {

    public static void main(String[] args) {
        printMem();
        byte[] b = new byte[1 * 1024 * 1024];
        System.out.println("分配了1M空间给数组");
        printMem();
        b = new byte[4 * 1024 * 1024];
        System.out.println("分配了4M空间给数组");
        printMem();
    }

    private static void printMem() {
        System.out.println("maxMemory=" + Runtime.getRuntime().maxMemory() + " bytes");
        System.out.println("free mem=" + Runtime.getRuntime().freeMemory() + " bytes");
        System.out.println("total mem=" + Runtime.getRuntime().totalMemory() + " bytes");
    }

}
