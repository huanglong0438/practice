package jianzhi_offer.interview53._1;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/8
 **/
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = binSearchLeft(nums, 0, nums.length-1, target);
        int right = binSearchRight(nums, 0, nums.length-1, target);
        if(left == -1 || right == -1) return 0;
        return right - left + 1;
    }

    private int binSearchLeft(int[] nums, int start, int end, int target) {
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (isFirstTarget(nums, mid, target)) {
                return mid;
            } else if (nums[mid] > target || (nums[mid] == target && nums[mid-1] == target)) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return -1;
    }

    private int binSearchRight(int[] nums, int start, int end, int target) {
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (isLastTarget(nums, mid, target)) {
                return mid;
            } else if (nums[mid] < target || (nums[mid] == target && nums[mid+1] == target)) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return -1;
    }

    private boolean isLastTarget(int[] nums, int index, int target) {
        return nums[index] == target && (index == nums.length-1 || nums[index+1] > target);
    }

    private boolean isFirstTarget(int[] nums, int index, int target) {
        return nums[index] == target && (index == 0 || nums[index-1] < target);
    }

}
