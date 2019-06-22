package practice.stacktrace;

/**
 * ForTestImpl
 *
 * @title ForTestImpl
 * @Description
 * @Author donglongcheng01
 * @Date 2019-06-19
 **/
public class ForTestImpl extends GenericTest implements ForTest {

    @Override
    public int add(int a, int b) {
        return calc(a, b);
    }


    public static void main(String[] args) {
        ForTest forTest = new ForTestImpl();
        System.out.println(forTest.add(1, 2));
    }
}
