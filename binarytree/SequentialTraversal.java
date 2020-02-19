package binarytree;

import java.util.Stack;

/**
 * 二叉树的中序遍历
 */
public class SequentialTraversal {
    /**
     * 递归实现
     * @param root 根节点
     */
    public void sequentialRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        sequentialRecursion(root.left);
        System.out.print(root.val + " ");
        sequentialRecursion(root.right);
    }

    /**
     * 非递归实现
     *
     * 思路：栈实现
     * 入栈根节点
     * 每入栈一个节点，就入栈对应左子节点
     * 没有左子节点后，出栈顶节点，并入栈出栈节点右子节点
     * 出栈时输出对应节点值
     * 循环直至栈空
     * @param root 根节点
     */
    public void sequential(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode node = root.left;
        while (!stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.val + " ");
            node = node.right;
            if (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        System.out.println();
    }
}
