package practice.jvm.classload.classloadinit;

/**
 * <p>UseParent
 * <p>{@code -XX:+TraceClassLoading} 可以看到Child加载了，但是没有初始化而已
 *
 * @title UseParent
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-29
 **/
public class UseParent {

    public static void main(String[] args) {
        System.out.println(Child.v);
    }

}
