package jianzhi_offer.interview52;

import jianzhi_offer.common.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        int listALength = len(headA);
        int listBLength = len(headB);
        int delta = listALength > listBLength ? (listALength - listBLength) : (listBLength - listALength);
        ListNode longerList = listALength > listBLength ? headA : headB;
        ListNode anotherList = listALength > listBLength ? headB : headA;
        for(int i = 0; i < delta; i++) longerList = longerList.next;
        ListNode node1 = longerList;
        ListNode node2 = anotherList;
        while(node1 != node2) {
            if(node1 == null || node2 == null) return null;
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    private int len(ListNode head) {
        int len = 0;
        for(ListNode node = head; node != null; node = node.next) {
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;
        Solution solution = new Solution();
        System.out.println(solution.getIntersectionNode(headA, headB).key);
    }
}
