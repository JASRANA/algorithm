package binarytree;

import java.util.Stack;

/**
 * 二叉树的后续遍历
 */
public class PostOrderTraversal {
    /**
     * 递归实现
     * @param root 根节点
     */
    public void postOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursion(root.left);
        postOrderRecursion(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 非递归实现
     *
     * 思路：栈实现
     * 入栈根节点
     * 每入栈一个节点，就入栈对应左子节点
     * 没有左子节点后，出栈栈顶节点，入栈其父节点的右子节点
     * 出栈时输出对应节点值
     * 循环直至栈空
     * @param root 根节点
     */
    public void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode node = root.left;
        while (!stack.isEmpty()) {

        }
    }
}
