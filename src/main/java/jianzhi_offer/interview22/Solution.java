package jianzhi_offer.interview22;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/25
 **/
public class Solution {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // 1 -> 2 -> 3 -> 4 -> 5 -> 6, k=3
    // 1, k=3
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode node = head;
        for (int i = 0; i < k; i++) {
            if (node == null) {
                return null; // size of list is less than k.
            }
            node = node.next;
        }
        ListNode kthNode = head;
        while(node != null) {
            node = node.next;
            kthNode = kthNode.next;
        }
        return kthNode;
    }

}
