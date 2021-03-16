package jianzhi_offer.interview16;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/16
 **/
public class Solution {

    public double power(double base, int exponent) {
        boolean negative = exponent < 0;
        if (exponent == 0) {
            return 1; // note: include 0^0
        }
        if (base == 0) {
            return 0; // note: include 0^(-2)
        }
        int unsignedExponent = Math.abs(exponent);
        double rawResult = powerWithUnsignedExponent(base, unsignedExponent);
        return negative ? (1 / rawResult) : rawResult;
    }

    private double powerWithUnsignedExponent(double base, int unsignedExponent) {
        if (unsignedExponent == 1) {
            return base;
        }
        int halfExponent = unsignedExponent >> 1;
        double halfResult = powerWithUnsignedExponent(base, halfExponent);
        if ((unsignedExponent & 1) == 1) {
            return base * halfResult * halfResult;
        } else {
            return halfResult * halfResult;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.power(2D, 5));
        System.out.println(solution.power(2D, -3));
        System.out.println(solution.power(0, 15));
        System.out.println(solution.power(2.5, 0));
    }

}
