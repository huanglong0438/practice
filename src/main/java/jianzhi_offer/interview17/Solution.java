package jianzhi_offer.interview17;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/17
 **/
public class Solution {

    public void printToMaxOfDigits(int n) {
        if (n <= 0) {
            return;
        }
        int[] number = new int[n];
        while (increment(number)) {
            printNumber(number);
        }
    }

    private void printNumber(int[] number) {
        boolean isBeginning0 = true;
        for (int i = 0; i < number.length; i++) {
            if (isBeginning0 && number[i] != 0) {
                isBeginning0 = false;
            }
            if (!isBeginning0) {
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }

    private boolean increment(int[] number) {
        for (int i = number.length - 1; i >= 0; i--) {
            if (number[i] + 1 == 10) {
                number[i] = 0;
            } else {
                number[i]++;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.printToMaxOfDigits(3);
    }

}
