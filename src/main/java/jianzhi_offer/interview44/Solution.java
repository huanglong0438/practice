package jianzhi_offer.interview44;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/10
 **/
class Solution {
    public int findNthDigit(int n) {
        if (n < 0) {
            return -1;
        }
        int index = n;
        int digits = 1;
        while(true) {
            long numbers = countOfDigits(digits);
            if (index < numbers*digits) {
                return digitAtIndex(index, digits);
            }
            index -= (numbers*digits);
            digits++;
        }
    }

    private long countOfDigits(int digits) {
        if (digits == 1) {
            return 10;
        }
        return (long)Math.pow(10, digits-1) * 9;
    }

    private int digitAtIndex(int index, int digits) {
        long num = beginNumber(digits) + (index/digits);
        int pos = digits - (index % digits) - 1;
        for (int i = 0; i < pos; i++) {
            num /= 10;
        }
        return (int) (num % 10);
    }

    private int beginNumber(int digits) {
        if (digits == 1) {
            return 0;
        }
        return (int)Math.pow(10, digits-1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findNthDigit(1000000000));
    }

}
