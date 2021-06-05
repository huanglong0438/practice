package jianzhi_offer.interview51;

/**
 * @Author: donglongcheng
 * @Description:
 * @Date: Create in 1:18 2021/6/6
 */
class Solution {
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] clones = new int[nums.length];
        return reversePairsCore(nums, 0, nums.length-1, clones);
    }

    private int reversePairsCore(int[] nums, int left, int right, int[] clones) {
        if(left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairsCore(nums, left, mid, clones);
        int rightPairs = reversePairsCore(nums, mid+1, right, clones);
        if(nums[mid] < nums[mid+1]) {
            return leftPairs + rightPairs;
        }
        int crossPairs = mergeAndCount(nums, left, mid, right, clones);
        return leftPairs + rightPairs + crossPairs;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] clones) {
        System.arraycopy(nums, left, clones, left, right + 1 - left);
        int pairs = 0;
        int i = left;
        int j = mid+1;
        int cur = left;
        while(i <= mid && j <= right) {
            if(clones[i] <= clones[j]) {
                nums[cur++] = clones[i++];
            } else {
                pairs += (mid-i+1);
                nums[cur++] = clones[j++];
            }
        }
        while(i <= mid) nums[cur++] = clones[i++];
        while(j <= right) nums[cur++] = clones[j++];
        return pairs;
    }
}
