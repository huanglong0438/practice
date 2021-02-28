package jianzhi_offer.interview6;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/26
 **/
public class Solution {

    public void revertPrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node.key);
            node = node.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        {
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);
        }
        Solution solution = new Solution();
        solution.revertPrint(head);
        solution.revertPrint(null);
    }

}
