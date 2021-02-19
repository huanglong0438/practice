package practice.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ExampleInvocationHandler implements InvocationHandler {

    private Object object;

    public ExampleInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=====");
        for (Object obj : args) {
            System.out.println(obj.toString());
        }
        System.out.println("======");
        return method.invoke(object, args);
    }

}
