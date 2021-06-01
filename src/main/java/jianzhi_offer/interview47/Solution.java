package jianzhi_offer.interview47;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/31
 **/
class Solution {
    public int maxValue(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] dp = new int[grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                int up = i == 0 ? 0 : dp[j];
                int left = j == 0 ? 0 : dp[j-1];
                dp[j] = Math.max(up, left) + grid[i][j];
            }
        }
        return dp[dp.length-1];
    }
}
