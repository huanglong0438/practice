package jianzhi_offer.interview39;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/25
 **/
public class Solution2 {

    // https://www.zhihu.com/question/49973163/answer/617122734
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums should not be empty");
        }
        int winner = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == winner) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    winner = nums[i];
                    count = 1;
                }
            }
        }
        return winner;
    }

}
