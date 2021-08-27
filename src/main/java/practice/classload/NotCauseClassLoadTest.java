package practice.classload;

import org.junit.Test;
import practice.classload.util.ClassLoadUtil;
import practice.classload.util.ClassLoadUtilChild;

/**
 * 以下情况不会触发类的初始化：
 * 1. 通过子类引用父类的静态字段，只会触发父类的初始化，不会触发子类的初始化
 * 2. 定义对象数组
 * 3. 引用static final常量
 * 4. 通过类名获取Class对象
 * 5. 通过Class.forName加载类时，指定initialize=false
 * 6. 通过ClassLoader默认的loadClass方法
 *
 * @title ClassLoadTest
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/27
 **/
public class NotCauseClassLoadTest {

    /**
     * 1. 通过子类引用父类的静态字段，只会触发父类的初始化，不会触发子类的初始化
     */
    @Test
    public void childImportParentStaticField() {
        System.out.println(ClassLoadUtilChild.STATIC_CONSTANT_2);
    }

    /**
     * 2. 定义对象数组
     */
    @Test
    public void instanceArray() {
        ClassLoadUtil[] classLoadUtils = new ClassLoadUtil[10];
    }

    /**
     * 3. 引用static final常量
     */
    @Test
    public void staticFinalWillNotLoadClass() {
        System.out.println(ClassLoadUtil.CONSTANT_1);
    }

    /**
     * 4. 通过类名获取Class对象
     */
    @Test
    public void getClassByName() {
        Class clazz = ClassLoadUtil.class;
    }

    /**
     * 5. 通过Class.forName加载类时，指定initialize=false
     */
    @Test
    public void classForNameWithoutInitialize() throws Exception {
        Class clazz = Class.forName("practice.classload.util.ClassLoadUtil", false,
                Thread.currentThread().getContextClassLoader());
    }

    /**
     * 6. 通过ClassLoader默认的loadClass方法
     */
    @Test
    public void classLoaderLoadClass() throws Exception {
        Class clazz = Thread.currentThread().getContextClassLoader()
                .loadClass("practice.classload.util.ClassLoadUtil");
    }

}
