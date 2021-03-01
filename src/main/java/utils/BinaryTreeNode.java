package utils;

/**
 * BinaryTreeNode
 *
 * @title BinaryTreeNode
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/28
 **/
public class BinaryTreeNode {

    public int value;

    public BinaryTreeNode left;

    public BinaryTreeNode right;

    public BinaryTreeNode parent;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode() {
    }

}
