package practice.jvm.classload.clinitdeadlock;

/**
 * StaticA
 *
 * @title StaticA
 * @Description
 * @Author donglongcheng01
 * @Date 2019-09-03
 **/
public class StaticA {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        try {
            Class.forName("practice.jvm.classload.clinitdeadlock.StaticB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("StaticA init OK");
    }
}
