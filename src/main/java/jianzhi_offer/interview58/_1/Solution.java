package jianzhi_offer.interview58._1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/24
 **/
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (sb.length() == 0) {
                    continue;
                }
                stack.push(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (sb.length() != 0) {
            stack.push(sb.toString());
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
            result.append(' ');
        }
        if (result.length() >= 1) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
