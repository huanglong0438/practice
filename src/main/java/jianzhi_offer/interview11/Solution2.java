package jianzhi_offer.interview11;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/9
 **/
public class Solution2 {

    /**
     * 1. accumulate eg. 1,2,3,4,5
     * 2. same number eg. 3,3,3,3,3
     */
    public int findMinOfRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("arrays is empty");
        }
        int left = 0;
        int right = array.length - 1;
        int mid = left;
        while (array[left] >= array[right]) {
            if (right - left == 1) {
                mid = right;
                break;
            }
            mid = (right - left) >> 1 + left;
            if (array[mid] == array[left] && array[mid] == array[right]) {
                return findInOrder(array, left, right);
            }
            if (array[left] <= array[mid]) { // min value in right region
                mid = left;
            } else if (array[mid] <= array[right]) { // min value in left region
                mid = right;
            }
        }
        return array[mid];
    }

    private int findInOrder(int[] array, int left, int right) {
        int result = array[left];
        for (int i = left+1; i <= right; i++) {
            result = Math.max(result, array[i]);
        }
        return result;
    }

}
