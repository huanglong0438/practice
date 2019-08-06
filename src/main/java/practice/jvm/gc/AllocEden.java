package practice.jvm.gc;

/**
 * 初始对象都会放到Eden区
 *
 * -Xmx64M -Xms64M -XX:+PrintGCDetails
 *
 * @title AllocEden
 * @Description
 * @Author donglongcheng01
 * @Date 2019-08-02
 **/
public class AllocEden {

    public static final int _1K = 1024;

    public static void main(String[] args) {
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
        }
    }

}
