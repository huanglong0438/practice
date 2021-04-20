package jianzhi_offer.interview36;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/19
 **/
public class MySolution {

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> stack = new LinkedList<>();
        Node node = root;
        Node head = new Node();
        Node preNode = head;
        // node == null means there's no right of last node. stack.isEmpty means last node is the most right node.
        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            joinNode(node, preNode);
            preNode = node;
            node = node.right;
        }
        // deal with head
        head.right.left = null;
        head = head.right;
        joinNode(head, preNode);
        return head;
    }

    private void joinNode(Node node, Node preNode) {
        preNode.right = node;
        node.left = preNode;
    }

    // Definition for a Node.
    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

}
