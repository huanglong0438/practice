package practice.jvm.classload.classloadinit;

/**
 * Parent
 *
 * @title Parent
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-29
 **/
public class Parent {
    static {
        System.out.println("Parent init");
    }
    public static int v = 100;
}
