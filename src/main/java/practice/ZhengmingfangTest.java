package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZhengmingfangTest implements Runnable{

    public ZhengmingfangTest(int count){
    }
    public ZhengmingfangTest(){

    }

    @Override
    public void run() {
        int counting = 0;
        cat(counting);
        System.out.println("run end");

    }
    public synchronized void cat(int count){
        while(count>1){

        }
        System.out.println("cat end");
    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        ExecutorService exe = Executors.newFixedThreadPool(2);

        Runnable test1 = new ZhengmingfangTest();
        Runnable test2 = new ZhengmingfangTest();

        exe.execute(test1);
        exe.execute(test2);

        System.out.println("fen ge");
        exe.shutdown();
    }
}