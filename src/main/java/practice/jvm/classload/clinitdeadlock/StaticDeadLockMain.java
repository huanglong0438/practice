package practice.jvm.classload.clinitdeadlock;

/**
 * StaticDeadLockMain
 *
 * @title StaticDeadLockMain
 * @Description
 * @Author donglongcheng01
 * @Date 2019-09-03
 **/
public class StaticDeadLockMain extends Thread {

    private char flag;

    public StaticDeadLockMain(char flag) {
        this.flag = flag;
        this.setName("Thread" + flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("practice.jvm.classload.clinitdeadlock.Static" + flag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " over");
    }

    public static void main(String[] args) {
        StaticDeadLockMain loadA = new StaticDeadLockMain('A');
        loadA.start();
        StaticDeadLockMain loadB = new StaticDeadLockMain('B');
        loadB.start();
    }
}
