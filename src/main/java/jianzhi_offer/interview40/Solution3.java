package jianzhi_offer.interview40;

import java.util.Map;
import java.util.TreeMap;

/**
 * Solution3 - red-black tree(TreeMap)
 *
 * @title Solution3
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/28
 **/
public class Solution3 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length == 0) {
            return new int[0];
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        for (int num : arr) {
            if (cnt < k) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                cnt++;
                continue;
            }
            int maxInMap = map.lastEntry().getKey();
            if (num < maxInMap) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                countDownOrRemove(map, maxInMap);
            }
        }
        int[] result = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                result[idx++] = entry.getKey();
            }
        }
        return result;
    }

    private void countDownOrRemove(TreeMap<Integer, Integer> map, int num) {
        if (map.get(num) == 1) {
            map.remove(num);
        } else {
            map.put(num, map.get(num) - 1);
        }
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        solution3.getLeastNumbers(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
    }

}
