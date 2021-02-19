package practice.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ExampleMethodInterceptor
 *
 * @title ExampleMethodInterceptor
 * @Description
 * @Author donglongcheng01
 * @Date 2020-03-18
 **/
public class ExampleMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("===before===");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("===after===");
        return result;
    }
}
