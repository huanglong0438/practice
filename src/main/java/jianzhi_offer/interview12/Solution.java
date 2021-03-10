package jianzhi_offer.interview12;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/10
 **/
public class Solution {

    public boolean findPathInMatrix(char[][] matrix, String str) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || str == null) {
            return false;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (doFindPathInMatrix(matrix, i, j, str, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean doFindPathInMatrix(char[][] matrix, int row, int col, String str, int index, boolean[][] visited) {
        if (index == str.length()) {
            return true;
        }
        boolean found = false;
        if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length
                && !visited[row][col] && index < str.length()
                && matrix[row][col] == str.charAt(index)) {
            visited[row][col] = true;
            index++;
            found = doFindPathInMatrix(matrix, row-1, col, str, index, visited)
                    || doFindPathInMatrix(matrix, row, col+1, str, index, visited)
                    || doFindPathInMatrix(matrix, row+1, col, str, index, visited)
                    || doFindPathInMatrix(matrix, row, col-1, str, index, visited);
            if (!found) {
                visited[row][col] = false;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'},
        };
        String str = "bfce";
        String str2 = "bfb";
        Solution solution = new Solution();
        System.out.println(solution.findPathInMatrix(matrix, str));
        System.out.println(solution.findPathInMatrix(matrix, str2));
    }


}
