package jianzhi_offer.interview11;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/5
 **/
public class Solution {

    public int findMinOfRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("empty array");
        }
        int left = 0;
        int right = array.length;
        while(left < right) {
            if (array[left] < array[right - 1]) { // not rotated
                return array[left];
            }
            int mid = left + ((right - left) / 2);
            if (mid == 0 || array[mid-1] > array[mid]) {
                return array[mid];
            }
            if (array[left] < array[mid]) { // means min value is in right region
                left = mid + 1;
            } else { // means min value is in left region
                right = mid;
            }
        }
        throw new IllegalStateException("should not be here");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[]{3, 4, 5, 3, 3, 3, 3};
        System.out.println(solution.findMinOfRotateArray(array));
    }


}
