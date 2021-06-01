package jianzhi_offer.interview46;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/26
 **/
class Solution2 {

    public int translateNum(int num) {
        if (num < 0) {
            return -1; // illegal input
        }
        int dp_next = 1;
        int dp_next2 = 1;
        int x = num % 10;
        int y = num % 10;
        while (num != 0) {
            num /= 10;
            x = num % 10;
            int temp = isAlpha(10 * x + y) ? (dp_next + dp_next2) : dp_next;
            dp_next2 = dp_next;
            dp_next = temp;
            y = x;
        }
        return dp_next;
    }

    private boolean isAlpha(int temp) {
        return temp >= 10 && temp <= 25;
    }

    public int translateNum2(int num) {
        int a = 1, b = 1, x, y = num % 10;
        while(num != 0) {
            num /= 10;
            x = num % 10;
            int tmp = 10 * x + y;
            int c = (tmp >= 10 && tmp <= 25) ? a + b : a;
            b = a;
            a = c;
            y = x;
        }
        return a;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.translateNum2(25));
        System.out.println(solution2.translateNum(25));
    }

}
