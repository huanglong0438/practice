package jianzhi_offer.interview2;

/**
 * Singleton4
 *
 * @title Singleton4
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/19
 **/
public class Singleton4 {

    private Singleton4(){}

    private static Singleton4 instance = new Singleton4();

    public static Singleton4 getInstance() {
        return instance;
    }
}
