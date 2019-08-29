package practice.jvm.asm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * <p>AsmHelloWorld
 * <p>继承了{@code ClassLoader}可以加载新生成的类
 * <p>实现了{@code Opcodes}可以使用ASM的一些常量
 * <p>《实战Java虚拟机》上抄的
 *
 * @title AsmHelloWorld
 * @Description 自定义Asm处理类，动态生成字节码
 * @Author donglongcheng01
 * @Date 2019-08-29
 **/
public class AsmHelloWorld extends ClassLoader implements Opcodes {

    public static void main(String[] args) throws Exception {
        // ASM自动计算生成最大局部变量表+最大操作数栈，自动计算栈映射帧
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        // 通过ClassWriter设置类的基本信息，public Example extends Object
        classWriter.visit(V1_7, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
        // 构造函数
        MethodVisitor methodVisitor =
                classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
        // public static void main(String[] args)
        methodVisitor = classWriter.visitMethod(
                ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("Hello World!");
        methodVisitor.visitMethodInsn(
                INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
        // to 字节码
        byte[] code = classWriter.toByteArray();

        // ClassLoader加载类，反射调用
        AsmHelloWorld loader = new AsmHelloWorld();
        Class exampleClass = loader.defineClass("Example", code, 0, code.length);
        exampleClass.getMethods()[0].invoke(null, new Object[]{null});

    }

}
