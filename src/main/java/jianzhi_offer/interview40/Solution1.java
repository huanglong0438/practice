package jianzhi_offer.interview40;

import java.util.Arrays;

/**
 * Solution1
 *
 * @title Solution1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/26
 **/
public class Solution1 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return new int[0];
        }
        int index = partition(arr, 0, arr.length-1);
        int start = 0;
        int end = arr.length-1;
        while(index != k-1) {
            if (index > k-1) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(arr, start, end);
        }
        return Arrays.copyOf(arr, k);
    }

    private int partition(int[] arr, int start, int end) {
        int firstOfBigger = start;
        int pivot = arr[end];
        for (int i = start; i < end; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, firstOfBigger);
                firstOfBigger++;
            }
        }
        swap(arr, firstOfBigger, end);
        return firstOfBigger;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
