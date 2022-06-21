package leetcode.solution394;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString("3[a]2[bc]"));
    }

    private StringBuilder sb = new StringBuilder();

    public String decodeString(String s) {
        int leftBrackets = 0;
        int start = 0;
        int end = 0;
        boolean findCode = false;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '[') {
                if(leftBrackets == 0) {
                    start=i;
                }
                findCode = true;
                leftBrackets++;
            }
            if(s.charAt(i) == ']') {
                leftBrackets--;
                if(leftBrackets == 0) {
                    end=i;
                    String decoded = decodeString(s.substring(start+1, end));
                    int repeat = findRepeats(s, start);
                    for(int r = 0; r < repeat; r++) {
                        sb.append(decoded);
                    }
                }
            }
        }
        if(!findCode) {
            return s;
        }
        return sb.toString();
    }

    private int findRepeats(String s, int start) {
        int res = 0;
        int factor = 1;
        for(int i = start-1; i>=0; i--) {
            if(s.charAt(i) < '0' || s.charAt(i) > '9') {
                return res;
            }
            int num = s.charAt(i) - '0';
            res += num*factor;
            factor *= 10;
        }
        return res;
    }
}
