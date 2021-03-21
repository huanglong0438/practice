package jianzhi_offer.interview17;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/17
 **/
public class Solution2 {

    public void printToMaxOfDigits(int n) {
        if (n <= 0) {
            return;
        }
        int[] number = new int[n];
        printToMaxOfDigitsRecursively(number, 0);
    }

    private void printToMaxOfDigitsRecursively(int[] number, int index) {
        if (index == number.length) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index] = i;
            printToMaxOfDigitsRecursively(number, index+1);
        }
    }

    private void printNumber(int[] number) {
        boolean isBeginningZero = true;
        for (int i = 0; i < number.length; i++) {
            if (isBeginningZero && number[i] != 0) {
                isBeginningZero = false;
            }
            if (!isBeginningZero) {
                System.out.print(number[i]);
            }
        }
        if (!isBeginningZero) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.printToMaxOfDigits(3);
    }


}
