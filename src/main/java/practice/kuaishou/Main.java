package practice.kuaishou;

/**
 * Main
 *
 * @title Main
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/14
 **/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        List<Integer> collection = new ArrayList(Arrays.asList(1,2,3,4,5,6));
        List<List<Integer>> result = new ArrayList<>();
        main.func(collection, 3, 0, new ArrayList<>(), result);
        System.out.println(result);
    }

    private void func(List<Integer> collection, int size, int index, List<Integer> temp, List<List<Integer>> result) {
        if(size == 1) {
            for(int i = index; i < collection.size(); i++) {
                List<Integer> res = new ArrayList<>(temp);
                res.add(collection.get(i));
                result.add(res);
            }
            return;
        }
        for(int i = index; i <= collection.size() - size; i++) {
            temp.add(collection.get(i));
            func(collection, size-1, i+1, temp, result);
            temp.remove(temp.size()-1);
        }
    }

}
