package practice.jvm.jmm;

/**
 * JMM有序性测试，JVM会对单线程进行<b>指令重排</b>，而多线程的时候会出现问题
 *
 * @title OrderExample
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-21
 **/
public class OrderExample {

    int a = 0;

    boolean flag = false;

    public void writer() {
        while (true) {
            synchronized (this) {
                a = 1;
                flag = true;
            }
            synchronized (this) {
                a = 0;
                flag = false;
            }
        }
    }

    public synchronized void reader() {
        // 这里期待的是writer执行后，a是1的时候再执行a+1，所以【预期输出永远是2】
        // 可是如果没有同步手段，就会导致writer的两个指令重排，结果可能出现1
        while (true) {
            if (flag) {
                int i = a + 1;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        OrderExample orderExample = new OrderExample();
        Thread t1 = new Thread(orderExample::writer);
        Thread t2 = new Thread(orderExample::reader);
        t1.start();
        t2.start();
    }

}
