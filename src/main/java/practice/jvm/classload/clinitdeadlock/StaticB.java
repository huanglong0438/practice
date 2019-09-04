package practice.jvm.classload.clinitdeadlock;

/**
 * StaticB
 *
 * @title StaticB
 * @Description
 * @Author donglongcheng01
 * @Date 2019-09-03
 **/
public class StaticB {

    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        try {
            Class.forName("practice.jvm.classload.clinitdeadlock.StaticA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("StaticB init OK");
    }

}
