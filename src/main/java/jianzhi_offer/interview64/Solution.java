package jianzhi_offer.interview64;

/**
 * Solution1
 *
 * @title Solution1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/9
 **/
public class Solution {
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

}
