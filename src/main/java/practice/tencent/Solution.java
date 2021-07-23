package practice.tencent;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/20
 **/
public class Solution {

    private boolean isLoop(ListNode root) {
        if (root == null) {
            return false;
        }
        ListNode slow = root;
        ListNode fast = slow;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    private static class ListNode {
        int val;
        ListNode next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode();
        node.val = 1;
        ListNode common = new ListNode();
        common.val = 2;
        node.next = common;
        node.next.next = new ListNode();
        node.next.next.val = 3;
        node.next.next.next = new ListNode();
        node.next.next.next.val = 4;
        node.next.next.next.next = common;
        System.out.println(new Solution().isLoop(node));
    }

}
