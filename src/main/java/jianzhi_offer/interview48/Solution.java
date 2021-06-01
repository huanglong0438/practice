package jianzhi_offer.interview48;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/31
 **/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        Set<Character> window = new HashSet<>(s.length());
        int longestLength = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            while(!window.contains(s.charAt(i))) {
                window.add(s.charAt(i));
                longestLength = Math.max(longestLength, window.size());
                if (++i >= s.length()) {
                    return longestLength;
                }
            }
            while(window.contains(s.charAt(i))) {
                window.remove(s.charAt(left++));
            }
            window.add(s.charAt(i));
        }
        return longestLength;
    }
}
