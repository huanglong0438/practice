package practice.exercise;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/22
 **/
public class Solution {

    static class TreeNode {
        String nodeName;
        List<TreeNode> children = new LinkedList<>();
        public TreeNode(String nodeName) {
            this.nodeName = nodeName;
        }
    }

    private String judgeInvalidNode(List<String> dependencies, List<String> failNodes) {
        Map<String, TreeNode> allNodes = new HashMap<>();
        for (String dependency : dependencies) {
            String child = dependency.split("-")[0];
            String parent = dependency.split("-")[1];
            allNodes.putIfAbsent(child, new TreeNode(child));
            allNodes.putIfAbsent(parent, new TreeNode(parent));
            allNodes.get(parent).children.add(allNodes.get(child));
        }
        for (String failNodeName : failNodes) {
            deleteNode(failNodeName, allNodes);
        }
        return StringUtils.join(allNodes.keySet(), ",");
    }

    private void deleteNode(String treeNodeName, Map<String, TreeNode> allNodes) {
        TreeNode failNode = allNodes.get(treeNodeName);
        allNodes.remove(failNode.nodeName);
        for (TreeNode childNode : failNode.children) {
            deleteNode(childNode.nodeName, allNodes);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.judgeInvalidNode(
                Lists.newArrayList("a1-a2", "a5-a6", "a2-a3", "a4-a1"),
                Lists.newArrayList("a5", "a2")));
    }

}
