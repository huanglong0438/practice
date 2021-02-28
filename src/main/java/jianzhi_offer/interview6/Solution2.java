package jianzhi_offer.interview6;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/26
 **/
public class Solution2 {

    public void revertPrint(ListNode head) {
        if (head == null) {
            return;
        }
        revertPrint(head.next);
        System.out.println(head.key);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        {
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);
        }
        Solution2 solution = new Solution2();
        solution.revertPrint(head);
        solution.revertPrint(null);
    }
    
}
