package jianzhi_offer.interview65;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/9
 **/
class Solution {
    public int add(int a, int b) {
        int sum = 0;
        int carry = 0;
        do {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        } while (b != 0);
        return sum;
    }
}
