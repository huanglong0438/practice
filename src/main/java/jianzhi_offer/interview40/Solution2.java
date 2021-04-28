package jianzhi_offer.interview40;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/26
 **/
public class Solution2 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length == 0) {
            return new int[0];
        }
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else {
                pq.poll();
                pq.offer(num);
            }
        }

        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num : pq) {
            res[idx++] = num;
        }
        return res;
    }

    public int[] getLeastNumbersManual(int[] arr, int k) {
        return null;
    }

}
