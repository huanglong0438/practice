package practice.jvm.jmm;

/**
 * OrderExampleWrong
 *
 * @title OrderExampleWrong
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-21
 **/
public class OrderExampleWrong {


    int a = 0;

    boolean flag = false;

    public void writer() {
        while (true) {
            a = 1;
            flag = true;
            a = 0;
            flag = false;
        }
    }

    public void reader() {
        // 这里期待的是writer执行后，a是1的时候再执行a+1，所以期待输出永远是2
        // 可是如果没有同步手段，就会导致writer的两个指令重排，结果可能出现1
        while (true) {
            if (flag) {
                int i = a + 1;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        OrderExampleWrong orderExample = new OrderExampleWrong();
        Thread t1 = new Thread(orderExample::writer);
        Thread t2 = new Thread(orderExample::reader);
        t1.start();
        t2.start();
    }

}
