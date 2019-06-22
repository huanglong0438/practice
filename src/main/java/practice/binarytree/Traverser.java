package practice.binarytree;

import java.util.LinkedList;

/**
 * Created by donglongcheng01 on 2018/4/4.
 */
public class Traverser {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(null, null, 2),
                new TreeNode(
                        new TreeNode(null, null, 4),
                        new TreeNode(null, null, 5),
                        3),
                1);

//        postOrder(root);
        postOrderNoneRecursive(root);

    }

    public static void preOrder(TreeNode root) {

        if (root == null) {
            return;
        }

        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void preOrderNoneRecursive(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                // 往左遍历到头，同时压栈缓存
                System.out.println(p.val);
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                p = p.right;
            }
        }
    }

    public static void inOrder(TreeNode root) {

        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void inOrderNoneRecursive(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode p = root;

        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                System.out.println(p.val);
                p = p.right;
            }
        }
    }

    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    public static void postOrderNoneRecursive(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur;
        TreeNode pre = null;
        stack.push(root);

        while (!stack.isEmpty()) {
            cur = stack.pop();
            if ((cur.left == null && cur.right == null)
                    || (pre != null && (pre == cur.left || pre == cur.right))) {
                // 如果当前节点是叶子节点，那就直接访问；或者当前节点的左右孩子遍历过了，也直接访问
                System.out.println(cur.val);
                pre = cur;
            } else {
                // 如果当前节点不是叶子节点并且也有孩子，那就依次把自己、右孩子、左孩子入栈
                stack.push(cur);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }

    }

    public static class TreeNode {

        TreeNode left;

        TreeNode right;

        int val;

        public TreeNode() {
        }

        public TreeNode(TreeNode left, TreeNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }
}
