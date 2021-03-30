package jianzhi_offer.interview28;

import jianzhi_offer.common.TreeNode;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/30
 **/
public class Solution {

    /*
            1
           / \
          2   2
         / \ / \
        3  4 4  3
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return areSymmetric(root.left, root.right);
    }

    private boolean areSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val
                && areSymmetric(root1.left, root2.right)
                && areSymmetric(root1.right, root2.left);
    }

}
