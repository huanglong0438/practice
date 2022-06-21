package leetcode.solution76;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for(int i = 0; i < t.length(); i++) {
            int cnt = tMap.getOrDefault(t.charAt(i), 0);
            tMap.put(t.charAt(i), cnt+1);
        }
        int need = tMap.size();
        int have = 0;
        Map<Character, Integer> sMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = -1;
        int minRight = -1;
        while(right < s.length()) {
            char ch = s.charAt(right);
            if(tMap.containsKey(ch)) {
                sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
                if(sMap.get(ch) == tMap.get(ch)) {
                    have++;
                }
                while(have == need) {
                    if(right - left + 1 < minLen) { // update min
                        minLen = right - left + 1;
                        minLeft = left;
                        minRight = right;
                    }
                    char leftCh = s.charAt(left);
                    if(tMap.containsKey(leftCh)) {
                        if(sMap.get(leftCh) == tMap.get(leftCh)) {
                            have--;
                        }
                        sMap.put(leftCh, sMap.get(leftCh)-1);
                    }
                    left++;
                }
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minRight+1);
    }
}
