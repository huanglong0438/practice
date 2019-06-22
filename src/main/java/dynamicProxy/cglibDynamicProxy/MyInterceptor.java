package dynamicProxy.cglibDynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by donglongcheng01 on 2017/7/31.
 */
public class MyInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("=======before method=========");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("=======after method==========");
        return result;
    }
}
