package practice.classload.util;

/**
 * ClassLoadConstant
 *
 * @title ClassLoadConstant
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/27
 **/
public class ClassLoadUtil {

    public static final int CONSTANT_1 = 1;

    public static int STATIC_CONSTANT_2 = 2;

    static {
        System.out.println(ClassLoadUtil.class.getName() + " is loaded.");
    }

    public static void staticFunction() {
        System.out.println("call staticFunction");
    }

    public static void main(String[] args) {
        System.out.println("main");
    }

}
