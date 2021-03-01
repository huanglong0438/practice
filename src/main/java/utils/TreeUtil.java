package utils;

import utils.BinaryTreeNode;

/**
 * TreeUtil
 *
 * @title TreeUtil
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/28
 **/
public class TreeUtil {

    public static void traversePreOrder(StringBuilder sb, String padding, String pointer, BinaryTreeNode node) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());
            sb.append("\n");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            paddingBuilder.append("│  ");

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.getRight() != null) ? "├──" : "└──";

            traversePreOrder(sb, paddingForBoth, pointerForLeft, node.getLeft());
            traversePreOrder(sb, paddingForBoth, pointerForRight, node.getRight());
        }
    }

}
