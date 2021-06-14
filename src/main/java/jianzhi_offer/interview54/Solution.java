package jianzhi_offer.interview54;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/14
 **/

import jianzhi_offer.common.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private int k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        TreeNode kthNode = kthLargestCore(root);
        if (kthNode == null) {
            return -1;
        }
        return kthNode.val;
    }

    private TreeNode kthLargestCore(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode kthNode = null;
        if (node.right != null) { // 右
            kthNode = kthLargestCore(node.right);
        }
        // 中
        if (kthNode != null) { // 已经找到该节点，返回
            return kthNode;
        } else {
            if (k == 1) { // 刚好找到，返回中节点
                return node;
            } else { // 尚未找到该节点，更新k的值
                k--;
            }
        }
        if (node.left != null) { // 左
            kthNode = kthLargestCore(node.left);
        }
        return kthNode;
    }
}
