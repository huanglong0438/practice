package practice.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * ExampleMain
 *
 * @title ExampleMain
 * @Description
 * @Author donglongcheng01
 * @Date 2020-03-18
 **/
public class ExampleMain {

    public void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        ExampleMain exampleMain = new ExampleMain();
        ExampleMethodInterceptor interceptor = new ExampleMethodInterceptor();

        Enhancer enhancer = new Enhancer();
        // 这里以interceptor为模式，创建了ExampleMain的子类，所以要求被代理的方法不能是final
        enhancer.setSuperclass(exampleMain.getClass());
        enhancer.setCallback(interceptor);
        ExampleMain proxy = (ExampleMain) enhancer.create();
        proxy.sayHello();
    }

}
