package practice;

/**
 *
 * fuck you
 *
 * Created by donglongcheng01 on 2017/9/22.
 */
public class Child extends Parent{
    public int m = 1;

    public Long l;

    public void doAction1() {
        System.out.println(m);
    }

    public Child() {
        System.out.println("child");
    }

    public static void main(String[] args) {
        Parent parent = new Child();
        parent.doAction1();
        parent.doAction2();
        System.out.println(parent.m);
        Child child = (Child) parent;
        System.out.println(child.m);
        child.doAction1();

        Child c = new Child();
    }
}
