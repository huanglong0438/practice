package jianzhi_offer.interview46;

/**
 * Solution1
 *
 * @title Solution1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/26
 **/
class Solution1 {

    public int translateNum(int num) {
        if (num < 0) {
            return -1; // illegal input
        }
        String numStr = num + "";
        int[] dp = new int[numStr.length()];
        for (int i = numStr.length()-1; i>=0; i--) {
            if (i == numStr.length() - 1) {
                dp[i] = 1;
                continue;
            }
            if (i == numStr.length() - 2) {
                dp[i] = isAlpha(numStr.substring(i, i+2)) ? 2 : 1;
                continue;
            }
            dp[i] = isAlpha(numStr.substring(i, i+2))
                    ? (dp[i+1] + dp[i+2]) : (dp[i+1]);
        }
        return dp[0];
    }

    private boolean isAlpha(String numStr) {
        try {
            if (numStr.charAt(0) == '0') {
                return false; // special logic
            }
            int num = Integer.parseInt(numStr);
            return num >= 0 && num <= 25;
        } catch (Exception e) {
            return false;
        }
    }

}
