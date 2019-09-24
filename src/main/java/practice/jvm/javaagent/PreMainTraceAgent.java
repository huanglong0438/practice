package practice.jvm.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

/**
 * PreMainTraceAgent
 *
 * @title PreMainTraceAgent
 * @Description 打包成jar，然后在java工程运行参数上加上-javaagent
 * @Author donglongcheng01
 * @Date 2019-09-23
 **/
public class PreMainTraceAgent {

    /**
     * java agent的premain方法，是java agent的入口，类似于main方法，传参是固定的
     *
     * @param agentArgs 通过命令行传递给java agent的参数
     * @param inst java agent字节码转换的工具，封装了一些常用的API
     * @throws ClassNotFoundException
     * @throws UnmodifiableClassException
     */
    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException {
        System.out.println("agentArgs:" + agentArgs);
        // 自定义了一个类转换器
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(
                    ClassLoader loader, String className, Class<?> classBeingRedefined,
                    ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException {
                System.out.println("load class: " + className);
                return classfileBuffer;
            }
        });

    }

}
