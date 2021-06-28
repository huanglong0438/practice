package jianzhi_offer.interview57._2;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/23
 **/
class Solution {

    public int[][] findContinuousSequence(int target) {
        int left = 1, right = 2;
        int sum = left + right;
        List<int[]> result = new ArrayList<>();
        int halfTarget = target >> 1;
        while (left <= halfTarget) {
            if (sum == target) {
                addContinuousSequence(result, left, right);
                right++;
                sum += right;
                continue;
            }
            if (sum < target) {
                right++;
                sum += right;
            } else {
                sum -= left;
                left++;
            }
        }
        return result.toArray(new int[0][]);
    }


    private void addContinuousSequence(List<int[]> result, int left, int right) {
        int[] sequence = new int[right - left + 1];
        int index = 0;
        for (int i = left; i <= right; i++) {
            sequence[index++] = i;
        }
        result.add(sequence);
    }

}
