package jianzhi_offer.interview31;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/31
 **/
public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null) {
            return false;
        }
        int pushedIndex = 0;
        int popedIndex = 0;
        Deque<Integer> stack = new LinkedList<>();
        while(popedIndex < popped.length) {
            if (stack.isEmpty() || stack.peek() != popped[popedIndex]) {
                if (pushedIndex >= pushed.length) {
                    return false;
                } else {
                    stack.push(pushed[pushedIndex++]);
                }
            } else {
                popedIndex++;
                stack.pop();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(solution.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }

}
