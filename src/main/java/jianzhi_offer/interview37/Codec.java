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
import java.util.ListIterator;
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
        List<String> resultList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) { // when all null quit
            TreeNode node = queue.poll();
            if (node == null) {
                resultList.add("null");
                queue.offer(null);
                queue.offer(null);
            } else {
                resultList.add(node.val + "");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        removeLastNulls(resultList);
        return resultList.toString();
    }

    private void removeLastNulls(List<String> resultList) {
        ListIterator<String> iterator = resultList.listIterator();
        while (iterator.hasPrevious()) {
            if (!"null".equals(iterator.next())) {
                break;
            }
            iterator.remove();
        }
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String trimmedData = data.substring(1, data.length()-1);
        String[] values = trimmedData.split(",");
        TreeNode root = null;
        for (int i = 0; i < values.length; i++) {
            if ("null".equals(values[i])) {
                continue;
            }
            TreeNode node = new TreeNode(Integer.parseInt(values[i]));
            if (root == null) {
                root = node;
            }
            int leftIndex = (i+1)*2 - 1;
            int rightIndex = (i+1)*2;
            if (leftIndex < values.length && !"null".equals(values[leftIndex])) {
                node.left = new TreeNode(Integer.parseInt(values[leftIndex]));
            }
            if (rightIndex < values.length && !"null".equals(values[rightIndex])) {
                node.right = new TreeNode(Integer.parseInt(values[rightIndex]));
            }
        }
        return root;
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
