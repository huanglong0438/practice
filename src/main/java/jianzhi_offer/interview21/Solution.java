package jianzhi_offer.interview21;

import java.util.function.Consumer;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/23
 **/
public class Solution {

    // normal: 1 2 3 4 5
    // all odd: 1 3 5
    // all even: 2 4 6
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int firstEven = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) { // odd
                swap(nums, i, firstEven);
                firstEven++;
            }
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Consumer<int[]> printer = (int[] nums) -> {
            StringBuilder builder = new StringBuilder();
            for (int num : nums) {
                builder.append(num).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            System.out.println(builder);
        };
        printer.accept(solution.exchange(new int[]{1, 2, 3, 4, 5}));
        printer.accept(solution.exchange(new int[]{1, 3, 5, 4, 2}));
        printer.accept(solution.exchange(new int[]{2, 4, 1, 3, 5}));
        printer.accept(solution.exchange(new int[]{1, 3, 5}));
        printer.accept(solution.exchange(new int[]{2, 4, 6}));
    }

}
