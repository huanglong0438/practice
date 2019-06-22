package clone;

/**
 * Created by donglongcheng01 on 2018/2/4.
 */
public class Main {
    public static void main(String[] args) {
//        Fucker fucker1 = new Fucker();
//        fucker1.fucked = 100;
//        fucker1.totalFuckTime = 1234567890123456789L;
//        fucker1.fuckedRate = .5;
//        fucker1.name = "ljc";
//        Fucker.FirstVictim victim = new Fucker.FirstVictim();
//        victim.name = "jq";
//        victim.age = 15;
//        fucker1.victim = victim;
//
//        try {
//            Fucker fucker2 = (Fucker) fucker1.clone();
//            fucker1.name = "mzd";
//            fucker1.victim.age = 12;
//            System.out.println("fucker1 : " + fucker1);
//            System.out.println("fucker2 : " + fucker2);
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

        Fucker fucker = new Fucker();
        fucker.name = "yaoyafeng";
        test(fucker);
        System.out.println(fucker);
    }


    public static void test(Fucker fucker) {
        Fucker fucker1 = new Fucker();
        fucker1.name = "dlc";
        fucker = fucker1;
    }
}
