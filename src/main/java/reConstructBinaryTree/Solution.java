package reConstructBinaryTree;

import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
/*
 * 解题思路，核心思路，递归，
 * 从先序里面找到第一个，就是根节点，然后从中序中找出左边的长度，右边的长度，分别是左右子树的长度
 * 然后再递归查左子树和右子树（重点就在于重新递归查的时候的定位）
 */

public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reConstructBinaryTreeRecursion(pre,0,pre.length,in,0,in.length);
    }
    
    public TreeNode reConstructBinaryTreeRecursion(int[] pre,int p_start,int p_end,int[] in,int i_start,int i_end){
        if(p_start == p_end || i_start == i_end)
            return null;
        int pos = findTarget(pre[p_start],in,i_start,i_end);
        int length = pos - i_start;
        TreeNode node = new TreeNode(pre[p_start]);
        node.left = reConstructBinaryTreeRecursion(pre,p_start+1,p_start+1+length,in,i_start,i_start+length);
        node.right = reConstructBinaryTreeRecursion(pre,p_start+1+length,p_end,in,pos+1,i_end);
		return node;
    }
     
    
    public int findTarget(int target,int [] in,int i_start,int i_end){
        for(int i = i_start; i < i_end; i++){
            if(in[i] == target)
                return i;     
        }
        return -1;
    }
    
    public static void preVisit(TreeNode tree){
    	if(tree != null){
	    	System.out.println(tree.val);
	    	preVisit(tree.left);
	    	preVisit(tree.right);
    	}
    }
    
    public static void inVisit(TreeNode tree){
    	if(tree != null){
    		inVisit(tree.left);
    		System.out.println(tree.val);
    		inVisit(tree.right);
    	}
    }
    
    public static void main(String[] args) {
		Solution sol = new Solution();
		int[] pre = {1,2,3,4,5,6,7};
		int[] in = {3,2,4,1,6,5,7};
		TreeNode tree = sol.reConstructBinaryTree(pre, in);
		preVisit(tree);
		System.out.println("**********");
		inVisit(tree);
	}
    

    
}