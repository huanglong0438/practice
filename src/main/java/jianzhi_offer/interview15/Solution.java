package jianzhi_offer.interview15;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/15
 **/
public class Solution {

    public int numberOfOne(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((flag & n) != 0) {
                count++;
            }
            flag <<= 1;
        }
        return count;
    }

    public int numberOfOneBit(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfOne(9));
        System.out.println(solution.numberOfOneBit(9));
    }

}
