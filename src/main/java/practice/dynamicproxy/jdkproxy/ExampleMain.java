package practice.dynamicproxy.jdkproxy;

import java.lang.reflect.Proxy;

public class ExampleMain implements ExampleInterface{

    @Override
    public void function(int a, int b) {
        System.out.println(a + b);
    }

    public static void main(String[] args) {
        ExampleMain exampleMain = new ExampleMain();
        ExampleInvocationHandler handler = new ExampleInvocationHandler(exampleMain);
        // jdk的动态代理本质上是基于接口，构造了一个新的实现类
        ExampleInterface object = (ExampleInterface) Proxy.newProxyInstance(
                exampleMain.getClass().getClassLoader(), exampleMain.getClass().getInterfaces(), handler);
        object.function(1,2);
    }

}
