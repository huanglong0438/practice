package jianzhi_offer.interview53._3;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/9
 **/
class Solution {
    public int findNumberEqualIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == mid) {
                return mid;
            }
            if (nums[mid]<mid) {
                start = mid + 1;
            } else {
                end  = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-3, -1, 1, 3, 5};
        System.out.println(solution.findNumberEqualIndex(nums));
    }

}
