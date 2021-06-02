package jianzhi_offer.interview49;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/1
 **/
class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int m2 = 0;
        int m3 = 0;
        int m5 = 0;
        int[] uglyNumbers = new int[n];
        uglyNumbers[0]=1;
        for (int index = 1; index < n; index++) {
            int nextUgly = min(uglyNumbers[m2]*2, uglyNumbers[m3]*3, uglyNumbers[m5]*5);
            uglyNumbers[index] = nextUgly;
            while(uglyNumbers[m2] * 2 <= uglyNumbers[index]) {
                m2++;
            }
            while(uglyNumbers[m3] * 3 <= uglyNumbers[index]) {
                m3++;
            }
            while(uglyNumbers[m5] * 5 <= uglyNumbers[index]) {
                m5++;
            }
        }
        return uglyNumbers[n-1];
    }

    private int min(int a, int b, int c) {
        int relativeMin = Math.min(a,b);
        return Math.min(relativeMin, c);
    }
}
