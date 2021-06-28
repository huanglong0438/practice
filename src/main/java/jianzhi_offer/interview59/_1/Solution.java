package jianzhi_offer.interview59._1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/25
 **/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0 || nums.length < k) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        int left = 1 - k;
        int right = 0;
        while(right < nums.length) {
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[right]) deque.removeLast();
            deque.addLast(right);
            if (!deque.isEmpty() && deque.getFirst() < left) deque.removeFirst();
            if (left >= 0) res[index++] = nums[deque.getFirst()];
            left++;
            right++;
        }
        return res;
    }
}
