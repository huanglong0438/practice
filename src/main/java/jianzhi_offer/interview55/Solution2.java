package jianzhi_offer.interview55;

/**
 * Solution2
 *
 * @title Solution2
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
class Solution2 {

    private static final int DEPTH_NOT_BALANCE = -1;

    public boolean isBalanced(TreeNode root) {
        return getDepthOrBalance(root) != DEPTH_NOT_BALANCE;
    }

    private int getDepthOrBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getDepthOrBalance(node.left);
        if (leftDepth == DEPTH_NOT_BALANCE) {
            return leftDepth;
        }
        int rightDepth = getDepthOrBalance(node.right);
        if (rightDepth == DEPTH_NOT_BALANCE) {
            return rightDepth;
        }
        int diff = leftDepth - rightDepth;
        if (diff > 1 || diff < -1) {
            return DEPTH_NOT_BALANCE;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
