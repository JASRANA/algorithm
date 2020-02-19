package binarytree;

import java.util.Stack;

/**
 * 二叉树的前序遍历
 */
public class PreOrderTraversal {
    /**
     * 递归实现
     * @param root 根节点
     */
    public void preOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }

    /**
     * 非递归实现
     *
     * 思路：栈实现
     * 入栈根节点
     * 每入栈一个节点，输出对应节点值，并入栈左子节点
     * 没有左子节点后，出栈顶节点，并入栈对应右子节点
     * 循环直至栈空
     * @param root 根节点
     */
    public void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        System.out.print(root.val + " ");

        TreeNode node = root.left;
        while (!stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                System.out.print(node.val + " ");
                node = node.left;
            }
            node = stack.pop().right;
            if (node != null) {
                stack.push(node);
                System.out.print(node.val + " ");
                node = node.left;
            }
        }
        System.out.println();
    }
}
