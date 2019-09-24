package practice.jvm.bytecode;

/**
 * javap -v Calc.class查看字节码
 *
 * @title Calc
 * @Description
 * @Author donglongcheng01
 * @Date 2019-09-11
 **/
public class Calc {

    /**
     * <pre>
     *     编译生成的字节码如下：
     *          0: sipush        500 // 500超出了1字节范围，所以用sipush（2字节），500入操作数栈
     *          3: istore_1 // 操作数栈弹出，放入局部变量表槽位1
     *          4: sipush        500    // 同上
     *          7: istore_2 // 同上
     *          8: bipush        50 // 50没超出1字节，用bipush
     *         10: istore_3 // 同上
     *         11: iload_1  // 从局部变量表槽位1取数到操作数栈
     *         12: iload_2  // 从局部变量表槽位2取数到操作数栈
     *         13: iadd // 操作数栈相加
     *         14: iload_3  // 从局部变量表槽位3取数到操作数栈
     *         15: idiv // 操作数栈相除
     *         16: ireturn // 返回，将操作数栈顶元素弹出，压入调用者函数的操作数栈（如果是synchronized方法，隐含执行monitorexit退出临界区 ）
     * </pre>
     *
     * @return
     */
    public int calc() {
        int a = 500;
        int b = 500;
        int c = 50;
        return (a + b) / c;
    }

}
