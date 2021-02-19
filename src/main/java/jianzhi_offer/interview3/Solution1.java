package jianzhi_offer.interview3;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: donglongcheng
 * @Description:
 * @Date: Create in 22:55 2021/2/19
 */
public class Solution1 {

    public boolean duplicate(int[] numbers, int length, List<Integer> duplication) {
        boolean duplicate = false;
        Map<Integer, Integer> dict = new HashMap<>(length);
        for(int num : numbers) {
            if(dict.containsKey(num)) {
                duplication.add(num);
                duplicate = true;
            } else {
                dict.put(num, 1);
            }
        }
        return duplicate;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        List<Integer> duplication = Lists.newArrayList();
        System.out.println(solution1.duplicate(new int[]{2, 3, 1, 0, 2, 5, 3}, 7, duplication));
        System.out.println(duplication);
    }

}
