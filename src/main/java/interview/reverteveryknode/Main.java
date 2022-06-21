package interview.reverteveryknode;

public class Main {

    static class ListNode {
        int val;
        Object obj;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);
        ListNode result = main.revertEveryKNode(node, 3);
        ListNode n = result;
        while(n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    private ListNode revertEveryKNode(ListNode node, int k) {
        ListNode head = node;
        ListNode lastNode = node;
        ListNode result = null;
        ListNode pre = null;
        boolean firstRevert = true;
        while(head != null) {
            lastNode = head;
            for(int i = 0; i < k-1; i++) {
                if(lastNode == null) {
                    break;
                }
                lastNode = lastNode.next;
            }
            ListNode next = lastNode != null ? lastNode.next : null;
            if (lastNode != null) {
                lastNode.next = null;
            }
            ListNode newHead = revert(head);
            if (firstRevert) {
                firstRevert = false;
                result = newHead;
            }
            if(pre != null) {
                pre.next = newHead;
            }
            pre = head;
            head = next;
        }
        return result;
    }

    private ListNode revert(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        ListNode next = node.next;
        while (node != null) {
            node.next = pre;
            pre = node;
            node = next;
            if (next != null) {
                next = next.next;
            }
        }
        return pre;
    }

}
