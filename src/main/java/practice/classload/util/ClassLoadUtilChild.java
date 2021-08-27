package practice.classload.util;

/**
 * ClassLoaderUtilChild
 *
 * @title ClassLoaderUtilChild
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/27
 **/
public class ClassLoadUtilChild extends ClassLoadUtil {

    static {
        System.out.println(ClassLoadUtilChild.class.getName() + " is loaded.");
    }

}
