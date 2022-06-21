package leetcode.common;


import java.util.List;

/**
 * ListNode
 *
 * @title ListNode
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/26
 **/
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val + "";
    }

    public static void print(ListNode head) {
        ListNode node = head;
        StringBuilder sb = new StringBuilder();
        while(node != null) {
            sb.append(node).append("->");
            node = node.next;
        }
        System.out.println(sb);
    }

    public static ListNode buildList(List<Integer> nums) {
        ListNode dummyHead = new ListNode(0);
        ListNode node = dummyHead;
        for (int num : nums) {
            node.next = new ListNode(num);
            node = node.next;
        }
        return dummyHead.next;
    }
}
