package jianzhi_offer.interview42;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/10
 **/
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        int curSum = 0;
        for (int num : nums) {
            curSum += num;
            if (num > curSum) {
                curSum = num;
            }
            max = Math.max(max, curSum);
        }
        return max;
    }
}
