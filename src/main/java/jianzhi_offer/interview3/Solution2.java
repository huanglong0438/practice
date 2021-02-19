package jianzhi_offer.interview3;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author: donglongcheng
 * @Description:
 * @Date: Create in 23:25 2021/2/19
 */
public class Solution2 {

    public boolean duplicate(int[] numbers, int length, List<Integer> duplication) {
        if (numbers == null || numbers.length == 0 || numbers.length == 1) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (numbers[i] < 0 || numbers[i] >= length) {
                return false;
            }
        }
        boolean duplicate = false;
        for(int i = 0; i < length; i++) {
            while(numbers[i] != i) {
                int target = numbers[i];
                if(numbers[target] == target) {
                    duplicate = true;
                    duplication.add(target);
                    break;
                } else {
                    int temp = numbers[target];
                    numbers[target] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        return duplicate;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        List<Integer> duplication = Lists.newArrayList();
        System.out.println(solution2.duplicate(new int[]{2, 3, 1, 0, 2, 5, 3}, 7, duplication));
        System.out.println(duplication);
    }

}
