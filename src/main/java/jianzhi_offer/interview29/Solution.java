package jianzhi_offer.interview29;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/30
 **/
public class Solution {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];
        int resultIndex = 0;
        int i = 0;
        int j = 0;
        int up = 0;
        int down = matrix.length;
        int left = 0;
        int right = matrix[0].length;
        while(up < down && left < right) {
            while(j < right) {
                result[resultIndex++] = matrix[i][j];
                j++;
            }
            j--;
            up++;
            i++;
            if (up >= down || left >= right) {
                break;
            }
            while(i < down) {
                result[resultIndex++] = matrix[i][j];
                i++;
            }
            i--;
            right--;
            j--;
            if (up >= down || left >= right) {
                break;
            }
            while(j >= left) {
                result[resultIndex++] = matrix[i][j];
                j--;
            }
            j++;
            down--;
            i--;
            if (up >= down || left >= right) {
                break;
            }
            while(i >= up) {
                result[resultIndex++] = matrix[i][j];
                i--;
            }
            i++;
            left++;
            j++;
        }
        return result;
    }

}
