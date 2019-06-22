package dynamicProxy.cglibDynamicProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by donglongcheng01 on 2017/7/31.
 */
public class Main {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        MyInterceptor myInterceptor = new MyInterceptor();
        enhancer.setSuperclass(SayHello.class);
        enhancer.setCallback(myInterceptor);
        SayHello sayHelloProxy = (SayHello) enhancer.create();
        sayHelloProxy.say();
    }
}
