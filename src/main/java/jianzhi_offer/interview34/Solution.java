package jianzhi_offer.interview34;

import jianzhi_offer.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/8
 **/
public class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        pathSumCore(root, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void pathSumCore(TreeNode root, int target, int sum, List<Integer> temp, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        sum += root.val;
        temp.add(root.val);
        if (root.left == null && root.right == null) { // it's a leaf node
            if (sum == target) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }
        if (root.left != null) {
            pathSumCore(root.left, target, sum, temp, result);
            temp.remove(temp.size() - 1);
        }
        if (root.right != null) {
            pathSumCore(root.right, target, sum, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(8);
        System.out.println(solution.pathSum(treeNode, 9));
    }

}
