package jianzhi_offer.interview3;

/**
 * Solution3
 *
 * @title Solution3
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/20
 **/
public class Solution3 {

    public int getDuplication(int[] numbers, int length) {
        if (numbers == null || numbers.length <= 1) {
            return -1;
        }
        int left = 1;
        int right = length - 1;
        int mid = (left + right) >> 1;
        while(left < right) {
            int leftCount = 0;
            int rightCount = 0;
            for (int i = 0; i < length; i++) {
                if(numbers[i] >= left && numbers[i] <= mid) {
                    leftCount++;
                } else if(numbers[i] > mid && numbers[i] <= right) {
                    rightCount++;
                }
            }
            if (leftCount > mid - left + 1) {
                right = mid;
            } else if (rightCount > right - mid) {
                left = mid+1;
            }
            mid = (left + right) >> 1;
        }
        return mid;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.getDuplication(new int[]{2, 3, 5, 4, 3, 2, 6, 7}, 8));
        System.out.println(solution3.getDuplication(new int[]{1, 1, 1, 1, 7, 7, 7, 7}, 8));
        System.out.println(solution3.getDuplication(null, 0));
    }
}
