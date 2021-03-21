package jianzhi_offer.interview19;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/19
 **/
public class Solution2 {

    public boolean matchPattern(String input, String pattern) {
        if (input == null || pattern == null) {
            return false;
        }
        return matchPatternCore(input, 0, pattern, 0);
    }

    private boolean matchPatternCore(String input, int inputIndex, String pattern, int patternIndex) {
        if (inputIndex == input.length() && patternIndex == pattern.length()) {
            return true;
        }
        if (inputIndex < input.length() && patternIndex == pattern.length()) {
            return false;
        }
        if (pattern.charAt(patternIndex + 1) == '*') {
            if (inputIndex < input.length() && isEqual(input.charAt(inputIndex), pattern.charAt(patternIndex))) {
                // 1. move on the next state
                // 2. stay on the current state
                // 3. ignore 'a*'
                return matchPatternCore(input, inputIndex + 1, pattern, patternIndex + 2)
                        || matchPatternCore(input, inputIndex + 1, pattern, patternIndex)
                        || matchPatternCore(input, inputIndex, pattern, patternIndex + 2);
            } else {
                // not equal(include input is over), we can only try ignore
                return matchPatternCore(input, inputIndex, pattern, patternIndex + 2);
            }
        } else if (isEqual(input.charAt(inputIndex), pattern.charAt(patternIndex))) {
            return matchPatternCore(input, inputIndex + 1, pattern, patternIndex + 1);
        } else {
            return false;
        }
    }

    private boolean isEqual(char ch, char patternChar) {
        return ch == patternChar || patternChar == '.';
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.matchPattern("aaa", "aaaa*"));
    }

}
