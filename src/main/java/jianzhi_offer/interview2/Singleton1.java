package jianzhi_offer.interview2;

/**
 * Singleton1
 *
 * @title Singleton1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/19
 **/
public class Singleton1 {

    private Singleton1(){}

    private static Singleton1 instance = null;

    /**
     * 线程不安全
     */
    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

}
