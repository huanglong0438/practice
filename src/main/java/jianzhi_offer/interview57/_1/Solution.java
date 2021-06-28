package jianzhi_offer.interview57._1;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/22
 **/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int i = 0, j = nums.length-1;
        while(i < j) {
            int twoSum = nums[i] + nums[j];
            if (twoSum == target) {
                return new int[]{nums[i], nums[j]};
            } else if (twoSum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
    }
}
