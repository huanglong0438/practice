package jianzhi_offer.interview19;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/19
 **/
public class Solution {

    public boolean matchPattern(String input, String pattern) {
        // illegal patterns are not checked.
        if (input == null || input.length() == 0 || pattern == null || pattern.length() == 0) {
            return false;
        }
        return matchPatternCore(input, 0, pattern, 0);
    }

    private boolean matchPatternCore(String input, int inputIndex, String pattern, int patternIndex) {
        if (inputIndex >= input.length()) {
            if (patternIndex >= pattern.length()) {
                return true;
            } else if (asterisk(pattern, patternIndex)) {
                return matchPatternCore(input, inputIndex, pattern, patternIndex + 2);
            } else {
                return false;
            }
        }
        if (patternIndex >= pattern.length()) {
            return false;
        }
        if (isEqual(input.charAt(inputIndex), pattern.charAt(patternIndex))) {
            return matchPatternCore(input, inputIndex+1, pattern, patternIndex+1);
        } else if (asterisk(pattern, patternIndex)) {
            return matchPatternCore(input, inputIndex, pattern, patternIndex+2)
                    || matchPatternCore(input, inputIndex, generatePattern(pattern, patternIndex), patternIndex);
        } else {
            return false;
        }
    }

    private boolean isEqual(char ch, char patternChar) {
        return ch == patternChar || patternChar == '.';
    }

    private boolean asterisk(String pattern, int patternIndex) {
        return patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex+1) == '*';
    }

    private String generatePattern(String pattern, int patternIndex) {
        return pattern.substring(0, patternIndex) + pattern.charAt(patternIndex) + pattern.substring(patternIndex);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.matchPattern("aaa", "ab*ac*a"));
        System.out.println(solution.matchPattern("aaa", "a.a"));
        System.out.println(solution.matchPattern("aaa", "aa.a"));
        System.out.println(solution.matchPattern("aaa", "ab*a"));
        System.out.println(solution.matchPattern("aaa", "aaa*a"));
        System.out.println(solution.matchPattern("aaa", "aaaa*"));
    }

}
