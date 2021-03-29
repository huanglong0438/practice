package jianzhi_offer.interview24;

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

    private static void printNode(Solution.ListNode head) {
        Solution.ListNode node = head.next;
        while (node != null) {
            System.out.print(node.val + " --> ");
            node = node.next;
        }
        System.out.println("null");
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode before = null;
        ListNode node = head;
        ListNode after = head.next;
        while(node != null) {
            node.next = before;
            if (after == null) {
                return node;
            }
            before = node;
            after = after.next;
            node = after;
        }
        throw new IllegalStateException("not supposed to be here.");
    }

}
