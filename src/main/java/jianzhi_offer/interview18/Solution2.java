package jianzhi_offer.interview18;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/19
 **/
public class Solution2 {

    private static class ListNode {
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
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

    public void deleteDuplicateNode(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode preNode = head;
        ListNode node = head.next;
        while(node != null) {
            boolean findDuplicate = false;
            ListNode nextNode = node.next;
            while(nextNode != null && nextNode.value == node.value) {
                findDuplicate = true;
                nextNode = nextNode.next;
            }
            // conjunct node and nextNode if find duplicated
            if (findDuplicate) {
                node.next = nextNode;
            }
            node = node.next;
            preNode = preNode.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(4);
        printNode(head);
        Solution2 solution2 = new Solution2();
        solution2.deleteDuplicateNode(head);
        printNode(head);

        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(1);
        printNode(head2);
        solution2.deleteDuplicateNode(head2);
        printNode(head2);
    }

}
