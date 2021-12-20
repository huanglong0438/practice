package leetcode.solution48;

class Solution {
    public void rotate(int[][] matrix) {
        for(int layer = 0; layer < matrix[0].length/2; layer++) {
            int begin = layer;
            int end = begin + matrix[0].length - 2*begin - 1;
            rotateLayer(matrix, begin, end);
        }
    }

    private void rotateLayer(int[][] matrix, int begin, int end) {
        int move = end - begin;
        int lower = begin;
        int upper = lower + matrix[0].length - 2*begin - 1;
        for(int i = begin; i < end; i++) {
            int[] curPos = new int[]{begin, begin};
            int[] nextPos = moveRightAndDown(curPos, move, lower, upper);
            int restored = matrix[nextPos[0]][nextPos[1]];
            matrix[nextPos[0]][nextPos[1]] = matrix[curPos[0]][curPos[1]];
            curPos = nextPos;

            nextPos = moveDownAndLeft(curPos, move, lower, upper);
            int restored2 = matrix[nextPos[0]][nextPos[1]];
            matrix[nextPos[0]][nextPos[1]] = restored;
            curPos = nextPos;

            nextPos = moveLeftAndUp(curPos, move, lower, upper);
            restored = matrix[nextPos[0]][nextPos[1]];
            matrix[nextPos[0]][nextPos[1]] = restored2;
            curPos = nextPos;

            nextPos = moveUpAndRight(curPos, move, lower, upper);
            restored2 = matrix[nextPos[0]][nextPos[1]];
            matrix[nextPos[0]][nextPos[1]] = restored;
        }
    }

    private int[] moveRightAndDown(int[] curPos, int move, int lower, int upper) {
        int i = curPos[0];
        int j = curPos[1];
        int curMoved = 0;
        while(curMoved < move && j < upper) {
            j++;
            curMoved++;
        }
        while(curMoved < move && i < upper) {
            i++;
            curMoved++;
        }
        return new int[]{i, j};
    }

    private int[] moveDownAndLeft(int[] curPos, int move, int lower, int upper) {
        int i = curPos[0];
        int j = curPos[1];
        int curMoved = 0;
        while(curMoved < move && i < upper) {
            i++;
            curMoved++;
        }
        while(curMoved < move && j > lower) {
            j--;
            curMoved++;
        }
        return new int[]{i, j};
    }

    private int[] moveLeftAndUp(int[] curPos, int move, int lower, int upper) {
        int i = curPos[0];
        int j = curPos[1];
        int curMoved = 0;
        while(curMoved < move && j > lower) {
            j--;
            curMoved++;
        }
        while(curMoved < move && i > lower) {
            i--;
            curMoved++;
        }
        return new int[]{i, j};
    }

    private int[] moveUpAndRight(int[] curPos, int move, int lower, int upper) {
        int i = curPos[0];
        int j = curPos[1];
        int curMoved = 0;
        while(curMoved < move && i > lower) {
            i--;
            curMoved++;
        }
        while(curMoved < move && j < upper) {
            j++;
            curMoved++;
        }
        return new int[]{i, j};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[3][3];
        matrix[0] = new int[]{1, 2, 3};
        matrix[1] = new int[]{4, 5, 6};
        matrix[2] = new int[]{7, 8, 9};
        solution.rotate(matrix);
        System.out.println(matrix);
    }
}
