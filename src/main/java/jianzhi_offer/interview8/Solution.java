package jianzhi_offer.interview8;

import utils.BinaryTreeNode;
import utils.TreeUtil;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/1
 **/
public class Solution {

    public BinaryTreeNode findInOrderNext(BinaryTreeNode root, BinaryTreeNode node) {
        // todo sp
        if (root == null || node == null) {
            return null;
        }
        if (node.right != null) {
            BinaryTreeNode next = node;
            while(next.left != null) {
                next = next.left;
            }
            return next;
        } else if(isLeftChild(node)) {
            return node.parent;
        } else {
            BinaryTreeNode father = node.parent;
            while(father != null) {
                if (isLeftChild(father)) {
                    return father.parent;
                }
                father = father.parent;
            }
            return null;
        }
    }

    private boolean isLeftChild(BinaryTreeNode node) {
        if (node == null || node.parent == null) {
            return false;
        }
        return node == node.parent.left;
    }

    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(1);
        node.left = new BinaryTreeNode(2);
        node.left.parent = node;
        node.left.left = new BinaryTreeNode(4);
        node.left.left.parent = node.left;
        node.left.right = new BinaryTreeNode(5);
        node.left.right.parent = node.left;
        node.right = new BinaryTreeNode(3);
        node.right.parent = node;
        node.right.left = new BinaryTreeNode(6);
        node.right.left.parent = node.right;
        node.right.right = new BinaryTreeNode(7);
        node.right.right.parent = node.right;
        node.left.right.left = new BinaryTreeNode(8);
        node.left.right.left.parent = node.left.right;
        node.left.right.right = new BinaryTreeNode(9);
        node.left.right.right.parent = node.left.right;

        StringBuilder builder = new StringBuilder();
        TreeUtil.traversePreOrder(builder, "", "", node);
        System.out.println(builder);

        System.out.println(new Solution().findInOrderNext(node, node.left.right.right).value);
    }

}
