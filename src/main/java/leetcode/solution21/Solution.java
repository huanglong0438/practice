package leetcode.solution21;

import leetcode.common.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = list1.val < list2.val ? list1 : list2;
        ListNode smaller = list1.val < list2.val ? list1 : list2;
        ListNode bigger = list1.val < list2.val ? list2 : list1;
        while(smaller != null && bigger != null) {
            // skip node that is smaller than bigger
            while(smaller.next != null && smaller.next.val < bigger.val) {
                smaller = smaller.next;
            }
            ListNode smallerNext = smaller.next;
            smaller.next = bigger;
            smaller = bigger;
            bigger = smallerNext;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        Solution solution = new Solution();
        ListNode res = solution.mergeTwoLists(list1, list2);
        while (res != null) {
            System.out.print(res.val + " -> ");
            res = res.next;
        }
    }
}
