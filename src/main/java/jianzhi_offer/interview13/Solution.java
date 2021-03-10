package jianzhi_offer.interview13;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/10
 **/
public class Solution {

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold <= 0 || rows <= 0 || cols <0) {
            return 0;
        }
        boolean[][] visited = new boolean[rows][cols];
        return movingCountCore(threshold, visited, 0, 0);
    }

    private int movingCountCore(int threshold, boolean[][] visited, int i, int j) {
        if (check(threshold, visited, i, j)) {
            visited[i][j] = true;
            return 1 + movingCountCore(threshold, visited, i-1, j) +
                    movingCountCore(threshold, visited, i, j+1) +
                    movingCountCore(threshold, visited, i+1, j) +
                    movingCountCore(threshold, visited, i, j-1);
        }
        return 0;
    }

    private boolean check(int threshold, boolean[][] visited, int i, int j) {
        return i >= 0 && i < visited.length
                && j >= 0 && j < visited[0].length
                && !visited[i][j]
                && getDigitSum(i, j) <= threshold;
    }

    private int getDigitSum(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.movingCount(2, 3, 4));
    }

}
