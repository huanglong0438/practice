package jianzhi_offer.interview14;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/12
 **/
public class Solution {

    public int cutRope(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("number n is negative.");
        }
        if (n <= 2) {
            return 1;
        }
        int[] maxProducts = new int[n+1];
        maxProducts[1] = 1;
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= len/2; i++) {
                int maxLeft = Math.max(maxProducts[i], i);
                int maxRight = Math.max(maxProducts[len - i], len - i);
                maxProducts[len] = Math.max(maxProducts[len], maxLeft * maxRight);
            }
        }
        return maxProducts[n];
    }

    public int cutRopeGreedy(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        int timesOf3 = n / 3;
        if (n - timesOf3 * 3 == 1) {
            timesOf3 -= 1;
        }

        int timesOf2 = (n - timesOf3 * 3) / 2;
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.cutRope(3));
        System.out.println(solution.cutRope(4));
        System.out.println(solution.cutRope(5));
        System.out.println(solution.cutRope(8));

        System.out.println(solution.cutRopeGreedy(3));
        System.out.println(solution.cutRopeGreedy(4));
        System.out.println(solution.cutRopeGreedy(5));
        System.out.println(solution.cutRopeGreedy(8));
    }

}
