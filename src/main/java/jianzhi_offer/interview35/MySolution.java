package jianzhi_offer.interview35;

import java.util.HashMap;
import java.util.Map;

/**
 * MySolution
 *
 * @title MySolution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/16
 **/
public class MySolution {

    public Node copyRandomList(Node head) {
        Map<Node, Node> nodeMap = new HashMap<>();
        Node originNode = head;
        Node resultHead = new Node(0);
        Node copyNode = resultHead;
        while(originNode != null) {
            copyNode.next = copyOrFindNode(originNode, nodeMap);
            copyNode.next.random = copyOrFindNode(originNode.random, nodeMap);
            copyNode = copyNode.next;
            originNode = originNode.next;
        }
        return resultHead.next;
    }

    private Node copyOrFindNode(Node originNode, Map<Node, Node> nodeMap) {
        if (originNode == null) {
            return null;
        }
        if (nodeMap.get(originNode) != null) {
            return nodeMap.get(originNode);
        }
        Node copyNode = new Node(originNode.val);
        nodeMap.put(originNode, copyNode);
        return copyNode;
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
