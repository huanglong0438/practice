package jianzhi_offer.p44;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/19
 **/
public class Solution {

    @Deprecated
    public boolean isContainsInTwoDimensionalArray(int[][] array, int a) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int left = 0;
        int right = array[0].length;
        while(left < right) {
            // first, Y
            int midRow = (left + right) / 2;
            if(a < array[0][midRow-1]) {
                right = midRow - 1;
            } else if(a > array[0][midRow]) {
                left = midRow;
            } else if(a == array[0][midRow-1] || a == array[0][midRow]) {
                return true;
            } else {
                int up = 0;
                int down = array.length;
                while(up < down) {
                    // then X
                    int midColumn = (up + down) / 2;
                    if(a < array[midColumn-1][midRow-1]) {
                        down = midColumn - 1;
                    } else if(a > array[midColumn][midRow-1]) {
                        up = midColumn;
                    } else if(a == array[midColumn-1][midRow-1] || a == array[midColumn][midRow-1]) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean find(int[][] matrix, int number) {
        return false;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Solution solution = new Solution();
        System.out.println(solution.isContainsInTwoDimensionalArray(array, 5));
        System.out.println(solution.isContainsInTwoDimensionalArray(null, 5));
        System.out.println(solution.isContainsInTwoDimensionalArray(array, 7));
        System.out.println(solution.isContainsInTwoDimensionalArray(array, 15));
        System.out.println(solution.isContainsInTwoDimensionalArray(array, 0));
    }

}
