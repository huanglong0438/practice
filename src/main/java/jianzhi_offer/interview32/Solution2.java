package jianzhi_offer.interview32;

import jianzhi_offer.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Solution2
 *
 * @title Solution2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/1
 **/
public class Solution2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> newQueue = new LinkedList<>();
            List<Integer> line = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                line.add(node.val);
                if (node.left != null) {
                    newQueue.offer(node.left);
                }
                if (node.right != null) {
                    newQueue.offer(node.right);
                }
            }
            result.add(line);
            queue = newQueue;
        }
        return result;
    }

    public List<List<Integer>> levelOrderFasterVersion(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                line.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(line);
        }
        return result;
    }
}
