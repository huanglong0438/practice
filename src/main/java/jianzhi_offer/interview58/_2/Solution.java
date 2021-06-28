package jianzhi_offer.interview58._2;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/25
 **/
class Solution {
    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() == 0 || n >= s.length()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int i = n; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
