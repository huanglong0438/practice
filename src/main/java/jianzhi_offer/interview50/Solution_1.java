package jianzhi_offer.interview50;

/**
 * Solution_1
 *
 * @title Solution_1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/2
 **/
public class Solution_1 {
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        int[] charMap = new int[256];
        for (int i = 0; i < s.length(); i++) {
            charMap[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (charMap[s.charAt(i)] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
