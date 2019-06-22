package alibaba;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglongcheng01 on 2017/8/25.
 */
public class Main2 {

    static class A{

    }

    static class B extends A{

    }

    public static void main(String[] args) {
        List<A> list = new ArrayList<A>();
        list.add(new B());
        List<? extends A> list1 = new ArrayList<>();
//        list1.add(B.class);
    }

}
