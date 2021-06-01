package jianzhi_offer.interview48;

import java.util.HashMap;
import java.util.Map;

/**
 * BetterSolution
 *
 * @title BetterSolution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/31
 **/
class BetterSolution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        Map<Character, Integer> char2Index = new HashMap<>();
        int longestLength = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (char2Index.containsKey(s.charAt(i))) {
                left = Math.max(left, char2Index.get(s.charAt(i)) + 1);
            }
            char2Index.put(s.charAt(i), i);
            longestLength = Math.max(longestLength, i-left+1);
        }
        return longestLength;
    }
}
