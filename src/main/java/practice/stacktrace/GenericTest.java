package practice.stacktrace;

/**
 * GenericTest
 *
 * @title GenericTest
 * @Description
 * @Author donglongcheng01
 * @Date 2019-06-19
 **/
public abstract class GenericTest {

    int calc(int a, int b){
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            System.out.println(this.getClass().isInterface());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a + b;
    }

}
