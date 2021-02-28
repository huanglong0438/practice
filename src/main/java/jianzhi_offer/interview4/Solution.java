package jianzhi_offer.interview4;

import org.springframework.util.Assert;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/22
 **/
public class Solution {

    public boolean find(int[][] matrix, int number) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col >= 0) {
            if(number < matrix[row][col]) {
                col--;
            } else if(number > matrix[row][col]) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(new Solution().find(matrix, 7));
        System.out.println(new Solution().find(matrix, 5));
        System.out.println(new Solution().find(matrix, 15));
        System.out.println(new Solution().find(matrix, 100));
        System.out.println(new Solution().find(null, 7));
    }

}
