package jianzhi_offer.interview25;

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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode baseList = l1.val < l2.val ? l1 : l2;
        ListNode branchList = baseList == l1 ? l2 : l1;
        ListNode baseNode = baseList.next;
        ListNode foreNode = baseList;
        ListNode branchNode = branchList;
        while(baseNode != null && branchNode != null) {
            if (baseNode.val <= branchNode.val) {
                foreNode = foreNode.next; // baseNode & foreNode walk forward
                baseNode = baseNode.next;
            } else {
                foreNode.next = branchNode; // merge branchNode into baseList and walk forward
                branchNode = branchNode.next;
                foreNode.next.next = baseNode;
                foreNode = foreNode.next;
            }
        }
        if (branchNode != null) {
            foreNode.next = branchNode;
        }
        return baseList;
    }

    public ListNode mergeTwoListsInBook(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode mergedHead;
        if (l1.val < l2.val) {
            mergedHead = l1;
            mergedHead.next = mergeTwoListsInBook(l1.next, l2);
        } else {
            mergedHead = l2;
            mergedHead.next = mergeTwoListsInBook(l1, l2.next);
        }
        return mergedHead;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
//        printNode(solution.mergeTwoLists(l1, l2));
        printNode(solution.mergeTwoListsInBook(l1, l2));
    }

}
