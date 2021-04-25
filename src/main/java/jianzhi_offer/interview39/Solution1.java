package jianzhi_offer.interview39;

/**
 * Solution1
 *
 * @title Solution1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/25
 **/
class Solution1 {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums should not be empty");
        }
        int middle = nums.length/2;
        int index = partition(nums, 0, nums.length);
        int start = 0;
        int end = nums.length;
        while(index != middle) {
            if (middle < index) {
                end = index;
            } else {
                start = index + 1;
            }
            index = partition(nums, start, end);
        }
        return nums[index];
    }

    // return the index of pivot
    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end-1];
        int firstOfLarger = start;
        for (int i = start; i < end-1; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, firstOfLarger);
                firstOfLarger++;
            }
        }
        swap(nums, firstOfLarger, end-1);
        return firstOfLarger;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        System.out.println(solution.majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }
}
