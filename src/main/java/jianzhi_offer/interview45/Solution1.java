package jianzhi_offer.interview45;

/**
 * Solution1
 *
 * @title Solution1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/14
 **/
class Solution1 {
    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        quickSort(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = partition(nums, start, end);
        quickSort(nums, start, index-1);
        quickSort(nums, index, end);
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int firstOfLarger = start;
        for (int i = start; i < end; i++) {
            if (concatCompare(nums[i],pivot) < 0) {
                swap(nums, i, firstOfLarger);
                firstOfLarger++;
            }
        }
        swap(nums, firstOfLarger, end);
        return firstOfLarger;
    }

    private int concatCompare(int a, int b) {
        return Long.parseLong(""+a+b) - Long.parseLong(""+b+a) > 0 ? 1 : -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
