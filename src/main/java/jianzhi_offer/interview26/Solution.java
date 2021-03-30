package jianzhi_offer.interview26;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/29
 **/
public class Solution {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        } else if (A == null) {
            return false;
        }
        return isRootSubStructure(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isRootSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        } else if (A == null) {
            return false;
        }
        return A.val == B.val && isRootSubStructure(A.left, B.left) && isRootSubStructure(A.right, B.right);
    }

}
