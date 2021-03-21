package jianzhi_offer.interview18;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/17
 **/
public class Solution {

    private static class ListNode {
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }
    }

    // require caller to ensure nodeToBeDeleted is in listHead.
    public void deleteNode(ListNode listHead, ListNode nodeToBeDeleted) {
        if (listHead == null || nodeToBeDeleted == null || listHead.next == null) {
            return;
        }
        if (nodeToBeDeleted.next == null) {
            ListNode node = listHead;
            while(node.next != null) {
                if (node.next == nodeToBeDeleted) {
                    node.next = null;
                    return;
                }
                node = node.next;
            }
            throw new IllegalStateException("nodeToBeDeleted is not in list!");
        } else {
            ListNode nextNode = nodeToBeDeleted.next;
            nodeToBeDeleted.value = nextNode.value;
            nodeToBeDeleted.next = nextNode.next;
            nextNode.next = null; // not important
        }
    }

    private static void printNode(ListNode head) {
        ListNode node = head.next;
        while (node != null) {
            System.out.print(node.value + " --> ");
            node = node.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        printNode(head);
        Solution solution = new Solution();
        solution.deleteNode(head, head.next.next);
        printNode(head);
        solution.deleteNode(head, head.next.next);
        printNode(head);
        solution.deleteNode(head, head.next);
        printNode(head);
    }

}
