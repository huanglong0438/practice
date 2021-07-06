package practice.tencent;

/**
 * PrintOddAndEven
 *
 * @title PrintOddAndEven
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/5
 **/
public class PrintOddAndEven {

    private static final Object oddObj = new Object();

    private static volatile boolean isOdd = false;

    /*
    就是有两个线程，一个线程打印奇数另一个打印偶数，它们交替输出
    偶线程：0
    奇线程：1
    偶线程：2
            ……
    奇线程：99
    偶线程：100

    使用wait和notify 实现
     */
    private void print() {
        Thread oddThread = new Thread(() -> {
            int i = 1;
            try {
                synchronized (oddObj) {
                    while (i <= 100) {
                        while (!isOdd) {
                            oddObj.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + ":\t" + i);
                        i += 2;
                        oddObj.notify();
                        isOdd = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread evenThread = new Thread(() -> {
            int i = 0;
            try {
                synchronized (oddObj) {
                    while (i <= 100) {
                        while (isOdd) {
                            oddObj.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + ":\t" + i);
                        i += 2;
                        oddObj.notify();
                        isOdd = true;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        oddThread.start();
        evenThread.start();
    }

    public static void main(String[] args) {
        PrintOddAndEven printOddAndEven = new PrintOddAndEven();
        printOddAndEven.print();
    }

}
