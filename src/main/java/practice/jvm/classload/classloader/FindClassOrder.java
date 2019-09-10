package practice.jvm.classload.classloader;

/**
 * <pre>
 * 加了参数
 * -Xbootclasspath/a:/Users/donglongcheng01/Documents/classloadertest
 * 会用{@code Boot ClassLoader}
 *
 * 否则会用{@code App ClassLoader}
 * </pre>
 *
 * @title FindClassOrder
 * @Description
 * @Author donglongcheng01
 * @Date 2019-09-05
 **/
public class FindClassOrder {

    public static void main(String[] args) {
        HelloLoader loader = new HelloLoader();
        loader.print();
    }

}
