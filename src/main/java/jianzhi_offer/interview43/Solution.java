package jianzhi_offer.interview43;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/5/10
 **/
class Solution {
    public int countDigitOne(int n) {
        if(n < 1) {
            return 0;
        }
        if(n < 10) {
            return 1;
        }
        int powOfTen = powOfTen(n);
        int highNum = n / (int)Math.pow(10, powOfTen);
        int remain = n % (int)Math.pow(10, powOfTen);
        if(highNum == 1) {
            return countDigitOneOfDecimal(powOfTen) + (remain + 1) + countDigitOne(remain);
        } else {
            return countDigitOneOfDecimal(powOfTen) * highNum + (int)Math.pow(10, powOfTen) + countDigitOne(remain);
        }
    }

    private int powOfTen(int n) {
        int res = 0;
        while(n != 0) {
            n /= 10;
            res++;
        }
        return res-1;
    }

    private int countDigitOneOfDecimal(int powOfTen) {
        if(powOfTen == 1) {
            return 1;
        }
        return countDigitOneOfDecimal(powOfTen-1) * 10 + (int)Math.pow(10, powOfTen-1);
    }
}
