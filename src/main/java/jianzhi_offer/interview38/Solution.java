package jianzhi_offer.interview38;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/22
 **/
class Solution {

    private Set<String> result = new HashSet<>();

    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        permutationCore(s.toCharArray(), 0, "");
        return result.toArray(new String[0]);
    }

    private void permutationCore(char[] s, int cur, String temp) {
        if (cur == s.length - 1) {
            result.add(temp + s[cur]);
            return;
        }
        for (int i = cur; i < s.length; i++) {
            swap(s, i, cur);
            permutationCore(s, cur+1, temp+s[cur]);
            swap(s, i, cur);
        }
    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Arrays.stream(solution.permutation("aabc")).forEach(System.out::println);
    }

}
