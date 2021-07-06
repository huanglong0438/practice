package jianzhi_offer.interview60;

import java.util.Arrays;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/30
 **/
public class Solution2 {

    public double[] dicesProbability(int n) {
        if (n < 1) {
            return null;
        }
        int[][] dp = new int[n][6*n+1];
        for (int num = 1; num <= 6; num++) {
            dp[0][num] = 1;
        }
        for (int times = 1; times < n; times++) {
            for(int num = times+1; num <= 6*n; num++) {
                for(int pips = 1; pips <= 6; pips++) {
                    if (pips >= num) {
                        break;
                    }
                    dp[times][num] += dp[times-1][num-pips];
                }
            }
        }
        double allPossibilities = Math.pow(6, n);
        double[] res = new double[6*n-n+1];
        int index = 0;
        for (int num = n; num <= 6*n; num++) {
            res[index++] = dp[n-1][num] / allPossibilities;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        double[] possibilities = solution2.dicesProbability(2);
        Arrays.stream(possibilities).forEach(System.out::println);
    }


}
