package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by donglongcheng01 on 2017/7/31.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    /**
     * 在这里搞个target，代理的时候会把要代理的对象填里面
     * @param target
     */
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里不判断方法名的话，println里面的方法可能会执行target.toString，这个toString也会被代理
        if (method.getName().equals("getName") || method.getName().equals("getAge")) {
            System.out.println("==================before method============================");
            long start = System.currentTimeMillis();
            // Object result = method.invoke(proxy, args); 可不敢用proxy啊，我的哥哥，
            // 这个proxy已经是被代理过的了，你在代理过的方法的基础上再代理就递归了
            Object result = method.invoke(target, args);
            long end = System.currentTimeMillis();
            System.out.println("========after method, cost " + (end - start) + "ms. =========");
            return result;
        } else {
            return method.invoke(proxy, args);
        }
    }
}
