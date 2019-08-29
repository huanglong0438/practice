# practice.jvm
《JVM实战》《深入理解Java虚拟机》等一些关于JVM特性的实验例子
 - practice.jvm.gc.AllocEden，对象分配到新生代
 - practice.jvm.gc.MaxTenuringThreshold，**对象晋升到老年代的过程**，可以顺着gc打印出的堆栈信息复盘对象一步步晋升到老年代的过程
 - practice.jvm.gc.PretenureSizeThreShold，**大对象直接进入老年代**的过程
 - practice.jvm.gc.UseTLAB，TLAB开始/关闭的性能对比
 - practice.jvm.lock，关于JVM偏向锁的性能对比测试
 - practice.jvm.jmm，关于JMM的特性的测试，例如可见性、指令重排
 - practice.jvm.asm，关于ASM动态生成字节码的Hello World，**体验下动态生成字节码和类加载**