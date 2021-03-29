package jianzhi_offer.interview23;

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

    public ListNode entryNodeOfLoop(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode meetingNode = findMeetingNode(head);
        if (meetingNode == null) {
            return null; // means there is no loop in list
        }
        int nodeCountOfLoop = getNodeCountOfLoop(meetingNode);
        ListNode foreNode = head;
        for (int i = 0; i < nodeCountOfLoop; i++) {
            foreNode = foreNode.next;
        }
        ListNode behindNode = head;
        while(foreNode != behindNode) {
            foreNode = foreNode.next;
            behindNode = behindNode.next;
        }
        return behindNode;
    }

    private ListNode findMeetingNode(ListNode head) {
        // assert head is not null
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }

    private int getNodeCountOfLoop(ListNode meetingNode) {
        // assert meetingNode is a loop which means there's no null in list
        ListNode node = meetingNode.next;
        int i = 1;
        while(node != meetingNode) {
            i++;
            node = node.next;
        }
        return i;
    }

    private static void printNode(ListNode head) {
        ListNode node = head.next;
        while (node != null) {
            System.out.print(node.val + " --> ");
            node = node.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode loopEntry;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = loopEntry = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = loopEntry;

        ListNode result = solution.entryNodeOfLoop(head);
        System.out.println(result.val);
    }

}
