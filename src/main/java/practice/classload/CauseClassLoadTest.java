package practice.classload;

import org.junit.Test;
import practice.classload.util.ClassLoadUtil;
import practice.classload.util.ClassLoadUtilChild;

/**
 * 以下情况会触发类的初始化：
 * 1. new出类的实例
 * 2. 读取类的静态字段
 * 3. 调用类的静态方法
 * 4. 反射调用
 * 5. 加载子类时
 * 6. 调用main方法
 *
 * @title CauseClassLoadTest
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/27
 **/
public class CauseClassLoadTest {

    /**
     * 1. new出类的实例
     */
    @Test
    public void newInstance() {
        ClassLoadUtil classLoadUtil = new ClassLoadUtil();
    }

    /**
     * 2. 读取类的静态字段
     */
    @Test
    public void staticWillLoadClass() {
        System.out.println(ClassLoadUtil.STATIC_CONSTANT_2);
    }

    /**
     * 3. 调用类的静态方法
     */
    @Test
    public void callStaticFunction() {
        ClassLoadUtil.staticFunction();
    }

    /**
     * 4. 反射调用
     */
    @Test
    public void reflect() throws Exception {
        ClassLoadUtil classLoadUtil = ClassLoadUtil.class.newInstance();
    }

    /**
     * 5. 加载子类
     */
    @Test
    public void loadChild() {
        ClassLoadUtilChild child = new ClassLoadUtilChild();
    }

    /**
     * 6. 调用main方法
     * {@see {@link practice.classload.util.ClassLoadUtil}}
     */

}
