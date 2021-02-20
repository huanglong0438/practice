package jianzhi_offer.interview2;

/**
 * Singleton5
 *
 * @title Singleton5
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/19
 **/
public class Singleton5 {

    private Singleton5(){}

    private static Singleton5 getInstance() {
        return Nested.instance;
    }

    private static class Nested {
        Nested() { }

        private static Singleton5 instance = new Singleton5();
    }
}
