package jianzhi_offer.interview35;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/16
 **/
public class Solution {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        copyNodeToNeighbor(head);
        copyRandom(head);
        return extractCopyList(head);
    }

    private void copyNodeToNeighbor(Node head) {
        Node originNode = head;
        while(originNode != null) {
            Node nextNode = originNode.next;
            originNode.next = new Node(originNode.val);
            originNode.next.next = nextNode;
            originNode = nextNode;
        }
    }

    private void copyRandom(Node head) {
        Node originNode = head;
        Node copyNode = head.next;
        while(originNode != null) {
            if (originNode.random != null) {
                copyNode.random = originNode.random.next;
            }
            if (copyNode.next == null) {
                break;
            }
            originNode = originNode.next.next;
            copyNode = copyNode.next.next;
        }
    }

    private Node extractCopyList(Node head) {
        Node originHead = head;
        Node originNode = originHead;
        Node copyHead = head.next;
        Node copyNode = copyHead;
        while(originNode.next.next != null) {
            originNode.next = originNode.next.next;
            originNode = originNode.next;
            copyNode.next = copyNode.next.next;
            copyNode = copyNode.next;
        }
        originNode.next = null;
        return copyHead;
    }

    // Definition for a Node.
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
