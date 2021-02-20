package jianzhi_offer.interview2;

/**
 * Singleton2
 *
 * @title Singleton2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/19
 **/
public class Singleton2 {

    private Singleton2() { }

    private static Singleton2 instance = null;

    /**
     * 低效率
     */
    public static Singleton2 getInstance() {
        synchronized (Singleton2.class) {
            if (instance == null) {
                instance = new Singleton2();
            }
            return instance;
        }
    }
}
