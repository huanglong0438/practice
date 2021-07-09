package jianzhi_offer.interview66;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/9
 **/
class Solution {
    public int[] constructArr(int[] a) {
        int[] c = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            if (i == 0) {
                c[i] = 1;
            } else {
                c[i] = c[i-1] * a[i-1];
            }
        }
        int[] d = new int[a.length];
        for (int i = a.length-1; i >= 0; i--) {
            if (i == a.length-1) {
                d[i] = 1;
            } else {
                d[i] = d[i+1] * a[i+1];
            }
        }
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = c[i] * d[i];
        }
        return b;
    }
}
