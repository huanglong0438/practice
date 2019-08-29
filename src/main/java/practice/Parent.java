package practice;

/**
 * Created by donglongcheng01 on 2017/9/22.
 */
public class Parent {
    public int m = 0;

    private String privateA = "private";

    protected String protectedA = "protected";

    public void doAction1() {
        System.out.println(m);
    }

    public void doAction2() {
        System.out.println(m);
    }

    public Parent() {
        System.out.println("parent");
    }
}
