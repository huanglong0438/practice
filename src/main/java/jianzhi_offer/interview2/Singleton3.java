package jianzhi_offer.interview2;

/**
 * Singleton3
 *
 * @title Singleton3
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/19
 **/
public class Singleton3 {

    private Singleton3(){}

    private static volatile Singleton3 instance = null;

    /**
     * Double check lock
     */
    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
