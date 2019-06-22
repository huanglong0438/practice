package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by donglongcheng01 on 2017/7/31.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserService userService = new UserServiceImpl();
        InvocationHandler handler = new MyInvocationHandler(userService);
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), handler);
        System.out.println(userServiceProxy.getName());
        System.out.println(userServiceProxy.getAge());
    }
}
