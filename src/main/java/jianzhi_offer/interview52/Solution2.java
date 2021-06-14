package jianzhi_offer.interview52;

import jianzhi_offer.common.ListNode;

/**
 * @Author: donglongcheng
 * @Description:
 * @Date: Create in 17:47 2021/6/6
 */
public class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2) {
            node1 = node1.next != null ? node1.next : headB;
            node2 = node2.next != null ? node2.next : headA;
        }
        return node1;
    }
}
