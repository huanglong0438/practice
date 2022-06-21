package algorithm;

import com.google.common.collect.Lists;
import leetcode.common.ListNode;
import lombok.val;

import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 *   public ListNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution20220621 {

    public static void main(String[] args) {
        Solution20220621 solution = new Solution20220621();
        ListNode head = ListNode.buildList(Lists.newArrayList(1, 2, 3, 4, 5));
        ListNode.print(solution.reverseBetween(head, 2, 4));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @param m int整型
     * @param n int整型
     * @return ListNode类
     */
    public ListNode reverseBetween (ListNode head, int m, int n) {
        // write code here
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode node = head;
        ListNode oldHead = head;
        ListNode headPre = pre;
        for(int i = 1; i <= n; i++) {
            if(i == m) {
                oldHead = node;
                headPre = pre;
            }
            if(i == n) {
                break;
            }
            pre = node;
            node = node.next;
        }
        ListNode next = node.next;
        node.next = null;
        ListNode newHead = reverse(oldHead);
        headPre.next = newHead;
        oldHead.next = next;
        return dummyHead.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        ListNode next = null;
        while(node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

}

