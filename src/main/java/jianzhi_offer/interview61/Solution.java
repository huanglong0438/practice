package jianzhi_offer.interview61;

import java.util.Arrays;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/2
 **/
class Solution {
    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        int countOfZero = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                countOfZero++;
                continue;
            }
            if (i > 0 && nums[i-1] > 0 && nums[i] > 0) {
                int gap = nums[i] - nums[i-1] - 1;
                if (gap == -1) {
                    return false;
                } else if (gap > countOfZero) {
                    return false;
                } else {
                    countOfZero -= gap;
                }
            }
        }
        return true;
    }
}
