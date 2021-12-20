package leetcode.solution692;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCnt = new HashMap<>();
        for(String word : words) {
            wordCnt.put(word, wordCnt.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((e1, e2) -> {
                    return e1.getKey().equals(e2.getKey())
                            ? e2.getKey().compareTo(e1.getKey())
                            : e1.getValue() - e2.getValue();
                });
        for(Map.Entry<String, Integer> entry : wordCnt.entrySet()) {
            pq.offer(entry);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.topKFrequent(new String[]{"i","love","leetcode","i","love","coding"}, 2));
    }
}
