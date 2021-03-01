package jianzhi_offer.interview7;

import utils.BinaryTreeNode;
import utils.TreeUtil;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/28
 **/
public class Solution {

    public BinaryTreeNode reBuildBinaryTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        return doRebuildBinaryTree(preorder, inorder, 0, 0, preorder.length);
    }

    private BinaryTreeNode doRebuildBinaryTree(int[] preorder, int[] inorder, int left1, int left2, int length) {
        if (length <= 0) {
            return null;
        }
        int root = preorder[left1];
        for (int i = 0; i < length; i++) {
            int mid = left2 + i;
            if (root == inorder[mid]) {
                BinaryTreeNode node = new BinaryTreeNode();
                node.value = root;
                node.left = doRebuildBinaryTree(preorder, inorder,
                        left1 + 1, left2, mid - left2);
                node.right = doRebuildBinaryTree(preorder, inorder,
                        left1 + mid - left2 + 1, mid + 1, left2 + length - 1 - mid);
                return node;
            }
        }
        // find no root, input data is wrong
        throw new IllegalArgumentException("find no root, input data is wrong.");
    }

    public static void main(String[] args) {
        {
            int[] preorder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
            int[] inorder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
            BinaryTreeNode tree = new Solution().reBuildBinaryTree(preorder, inorder);
            StringBuilder builder = new StringBuilder();
            TreeUtil.traversePreOrder(builder, "", "", tree);
            System.out.println(builder);
        }

        {
            StringBuilder builder = new StringBuilder();
            TreeUtil.traversePreOrder(builder, "", "",
                    new Solution().reBuildBinaryTree(null, null));
            System.out.println(builder);
        }

        {
            int[] preorder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
            int[] inorder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
            StringBuilder builder = new StringBuilder();
            TreeUtil.traversePreOrder(builder, "", "",
                    new Solution().reBuildBinaryTree(preorder, inorder));
            System.out.println(builder);
        }

        System.out.println(-2 % 4);
    }

}
