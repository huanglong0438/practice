/**
 * <p>java agent的相关功能测试，java agent是可以在运行时动态修改class字节码的工具，通过Instrumentation提供的API
 * <p>有两种方式切入java agent
 * <pre>
 * 1. 在java工程启动时加上参数-javaagent
 * 2. 在java工程启动后，自己写另一个工程，然后通过jkd中tools.jar中的类jps等命令将agent attach到jvm进程上（探针的秘密）
 * </pre>
 */
package practice.jvm.javaagent;