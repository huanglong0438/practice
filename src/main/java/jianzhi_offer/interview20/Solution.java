package jianzhi_offer.interview20;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/22
 **/
public class Solution {

    private int index = 0;

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();
        boolean numeric = scanInteger(s);
        if (index < s.length() && s.charAt(index) == '.') {
            index++;
            numeric = scanUnsignedInteger(s) || numeric;
        }
        if (index < s.length() && isExponent(s.charAt(index))) {
            index++;
            numeric = numeric && scanInteger(s);
        }
        return numeric && index == s.length();
    }

    private boolean scanInteger(String s) {
        if (index < s.length() && isPlusMinus(s.charAt(index))) {
            index++;
        }
        return scanUnsignedInteger(s);
    }

    private boolean scanUnsignedInteger(String s) {
        int before = index;
        while(index < s.length() && isDigit(s.charAt(index))) {
            index++;
        }
        return index > before;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isPlusMinus(char c) {
        return c == '+' || c == '-';
    }

    private boolean isExponent(char c) {
        return c == 'e' || c == 'E';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isNumber("-1E-16"));
        System.out.println(solution.isNumber("1a3.14"));
        System.out.println(solution.isNumber(".123"));
        System.out.println(solution.isNumber("."));
    }
}
