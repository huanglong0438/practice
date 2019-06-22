package multipleThread;

import java.util.concurrent.BlockingQueue;

/**
 * Created by donglongcheng01 on 2017/9/7.
 */
public class Product {

    private int product = 0;

    private static final int MAX_PRODUCT = 5;

    private static final int MIN_PRODUCT = 0;

    public synchronized void produce() {
        while (this.product > MAX_PRODUCT) {
            try {
                System.out.println("满了");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.product++;
        System.out.println("生产者生产第" + this.product + "个产品");
        notifyAll();
    }

    public synchronized void consume() {
        while (this.product <= MIN_PRODUCT) {
            try {
                System.out.println("缺货");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.product--;
        System.out.println("消费者消费第" + this.product + "个产品");
        notifyAll();
    }

    public static void main(String[] args) {
        Product product = new Product();
        Producer producer = new Producer(product);
        Consumer consumer = new Consumer(product);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }

    static class Producer implements Runnable {

        private Product product;

        public Producer(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("pro i: " + i);
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.produce();
            }
        }
    }


    static class Consumer implements Runnable {

        private Product product;

        public Consumer(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("con i: " + i);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.consume();
            }
        }
    }

}
