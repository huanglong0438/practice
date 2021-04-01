package jianzhi_offer.interview32;

import jianzhi_offer.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Solution3
 *
 * @title Solution3
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/1
 **/
public class Solution3 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        boolean reverted = false;
        while (!queue.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = reverted ? queue.pollLast() : queue.pollFirst();
                line.add(node.val);
                if (!reverted) {
                    if (node.left != null) queue.offerLast(node.left);
                    if (node.right != null) queue.offerLast(node.right);
                } else {
                    if (node.right != null) queue.offerFirst(node.right);
                    if (node.left != null) queue.offerFirst(node.left);
                }
            }
            reverted = !reverted;
            result.add(line);
        }
        return result;
    }
}
