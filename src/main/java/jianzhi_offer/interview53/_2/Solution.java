package jianzhi_offer.interview53._2;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/8
 **/
class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        while(start <= end) {
            int mid = start + (end-start)/2;
            if (isFirstMisMatch(nums, mid)) {
                return mid == 0 ? 0 : nums[mid]-1;
            } else if (nums[mid] == mid) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return nums[nums.length-1] + 1;
    }

    private boolean isFirstMisMatch(int[] nums, int index) {
        return nums[index] != index && (index == 0 || nums[index-1]==index-1);
    }
}
