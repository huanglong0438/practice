package jianzhi_offer.interview40;

/**
 * Solution2_2
 *
 * @title Solution2_2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/28
 **/
class Solution2_2 {
    // https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }
        int[] result = new int[k];
        int i = 0;
        while(i < k) {
            result[i] = arr[i]; // assert that k < arr.length
            i++;
        }
        buildHeap(result);
        while(i < arr.length) {
            if (arr[i] < result[0]) {
                result[0] = arr[i];
                adjust(result, 0);
            }
            i++;
        }
        return result;
    }

    private void buildHeap(int[] heap) {
        for (int i = heap.length/2 - 1; i >= 0; i--) {
            adjust(heap, i);
        }
    }

    private void adjust(int[] heap, int index) {
        int maxIndex = index;
        if (index*2+1 < heap.length && heap[index*2+1] > heap[maxIndex]) {
            maxIndex = index*2+1;
        }
        if (index*2+2 < heap.length && heap[index*2+2] > heap[maxIndex]) {
            maxIndex = index*2+2;
        }
        if (maxIndex != index) {
            swap(heap, index, maxIndex);
            adjust(heap, maxIndex);
        }
    }

    private void swap(int[] arr, int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
