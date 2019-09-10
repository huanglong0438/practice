package practice.jvm.classload.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 执行的过程中修改并重新编译替换DemoA.class文件实现【热加载】
 *
 * @title DoopRun
 * @Description
 * @Author donglongcheng01
 * @Date 2019-09-09
 **/
public class DoopRun {

    public static void main(String[] args) {
        while (true) {
            try {
                MyClassLoader loader = new MyClassLoader("/Users/donglongcheng01/Documents/workspace/" +
                        "myPractice/practice/target/classes/practice/jvm/classload/classloader/");
                Class cls = loader.loadClass("practice.jvm.classload.classloader.DemoA");
                Object demo = cls.newInstance();
                Method method = cls.getDeclaredMethod("hot", new Class[]{});
                method.invoke(demo, new Object[]{});
                Thread.sleep(10000);
            } catch (ClassNotFoundException | NoSuchMethodException
                    | IllegalAccessException | InstantiationException
                    | InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
