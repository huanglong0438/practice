package jianzhi_offer.interview60;

/**
 * Solution2_2
 *
 * @title Solution2_2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/30
 **/
public class Solution2_2 {

    public double[] dicesProbability(int n) {
        if (n < 1) {
            return null;
        }
        int[] dp = new int[6*n+1];
        for (int num = 1; num <= 6; num++) {
            dp[num] = 1;
        }
        for (int times = 1; times < n; times++) {
            int[] tmp = new int[6 * n + 1];
            for(int num = times+1; num <= 6*n; num++) {
                for(int pips = 1; pips <= 6; pips++) {
                    if (pips >= num) {
                        break;
                    }
                    tmp[num] += dp[num-pips];
                }
            }
            dp = tmp;
        }
        double allPossibilities = Math.pow(6, n);
        double[] res = new double[6*n-n+1];
        int index = 0;
        for (int num = n; num <= 6*n; num++) {
            res[index++] = dp[num] / allPossibilities;
        }
        return res;
    }

}
