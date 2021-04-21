package jianzhi_offer.interview37;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/20
 **/

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<String> resultList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                resultList.add("null");
            } else {
                resultList.add(node.val + "");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return listToString(resultList);
    }

    private String listToString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String num : list) {
            builder.append(num).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String trimmedData = data.substring(1, data.length() - 1);
        String[] nums = trimmedData.split(",");
        TreeNode root = generateTreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            node.left = generateTreeNode(nums[index]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            node.right = generateTreeNode(nums[index + 1]);
            if (node.right != null) {
                queue.offer(node.right);
            }
            index += 2;
        }
        return root;
    }

    private TreeNode generateTreeNode(String num) {
        if (num == null || "null".equals(num)) {
            return null;
        }
        return new TreeNode(Integer.parseInt(num));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
