package practice.jvm;

/**
 * 糟糕的finalize的使用
 * -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath="/Users/donglongcheng01/Downloads/f.dump"
 *
 * @title LongFinalize
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-05
 **/
@Deprecated
public class LongFinalize {

    public static class LF {
        private byte[] content = new byte[512];

        @Override
        protected void finalize() throws Throwable {
            try {
                System.out.println(Thread.currentThread().getId());
                // 模拟耗时操作
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            // 不断产生新的LF对象
            LF f = new LF();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }

}
