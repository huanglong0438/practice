package threadLocalTest;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * ThreadLocalTester
 *
 * @title ThreadLocalTester
 * @Description
 * @Author donglongcheng01
 * @Date 2019-01-19
 **/
public class ThreadLocalTester {

    // 静态变量ThreadLocal
    private static ThreadLocal<HashMap<String, Object>> envstore = new ThreadLocal<HashMap<String, Object>>() {
        @Override
        protected HashMap<String, Object> initialValue() {
            HashMap<String, Object> map = Maps.newHashMap();
            return map;
        }
    };

    private static InheritableThreadLocal<HashMap<String, Object>> inheritableEnvstore =
            new InheritableThreadLocal<HashMap<String, Object>>() {
                @Override
                protected HashMap<String, Object> initialValue() {
                    return Maps.newHashMap();
                }
            };

    public static Integer globalOptfrom;

    public static Integer getOptfrom() {
        return (Integer) envstore.get().get("optfrom");
    }

    public static Integer getInheritableOptfrom() {
        return (Integer) inheritableEnvstore.get().get("optfrom");
    }


    public static void main(String[] args) {
        // 在主线程设置了optfrom
        envstore.get().put("optfrom", 1);
        inheritableEnvstore.get().put("optfrom", 666);
        // 从主线程拿optfrom
        System.out.println(Thread.currentThread().getName() + ": " + getOptfrom());
        System.out.println(Thread.currentThread().getName() + ": " + getInheritableOptfrom());

        // 在主线程拿到，然后后面子线程用这个局部变量optfrom
        Integer optfrom = getOptfrom();
        // 同上，只不过设置到了static全局变量里
        globalOptfrom = optfrom;

        Thread t = new Thread(() -> {
            // 1. 直接读ThreadLocal，读不到的，只能是null
            System.out.println(Thread.currentThread().getName() + ": " + getOptfrom());
            // 2. 用InheritableThreadLocal，可以直接读到
            System.out.println(Thread.currentThread().getName() + ": " + getInheritableOptfrom());

            // 3. 通过局部变量传进来
            envstore.get().put("optfrom", optfrom);
            System.out.println(Thread.currentThread().getName() + ": " + getOptfrom());

            // 4. 通过全局变量传进来
            System.out.println(Thread.currentThread().getName() + ": " + globalOptfrom);

        });
        t.start();

    }


}
