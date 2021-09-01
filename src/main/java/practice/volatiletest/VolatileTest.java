package practice.volatiletest;

import java.util.concurrent.CountDownLatch;

/**
 * VolatileTest
 *
 * @title VolatileTest
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/27
 **/
public class VolatileTest {

    static volatile Person person = new Person();

    static Person person2 = person;

    public static void main(String[] args) throws InterruptedException {
        testVolatile();
    }

    public static void testVolatile() throws InterruptedException {
        person2.switch_ = false;
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                person2.switch_ = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (!person2.switch_) {
//            System.out.println(person.switch_ + "" + person.i);
            person2.i++;
        }
        System.out.println("stop");
    }


}
