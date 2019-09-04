package practice.jvm.classload.classloadinit;

/**
 * Child
 *
 * @title Child
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-29
 **/
public class Child extends Parent {

    static {
        System.out.println("Child init");
    }

}
