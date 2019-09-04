package practice.jvm.classload.classloader;

/**
 * 自底向上依此打印出来当前类的ClassLoader
 *
 * @title PrintClassLoaderTree
 * @Description
 * @Author donglongcheng01
 * @Date 2019-09-04
 **/
public class PrintClassLoaderTree {
    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
        // String这个类是核心类，是Boostrap Classloader加载的，所以打印不出来它的的class loader
        System.out.println(String.class.getClassLoader());
    }
}
