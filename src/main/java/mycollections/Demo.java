package mycollections;

import mycollections.mylists.ArrayList;
import mycollections.mylists.List;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by donglongcheng01 on 2018/2/12.
 */
public class Demo {

    public static void main(String[] args) {
        testMyArrayList();
    }

    public static void testMyArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
