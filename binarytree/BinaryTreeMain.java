package binarytree;

import java.util.Random;

class TreeNode {
    public TreeNode(int val) {
        this.val = val;
    }
    int val;
    TreeNode left;
    TreeNode right;
}

public class BinaryTreeMain {

    /**
     * 树数组
     */
    static int[][] tree;

    static Random random = new Random();

    public static void main(String[] args) {
        int maxLength = random.nextInt(10);
        tree = new int[maxLength][(int) Math.pow(2, maxLength)];
        int rootValue = random.nextInt(100);
        tree[0][0] = 1;
        tree[0][1] = rootValue;

        TreeNode root = new TreeNode(rootValue);
        makeTree(root, 0, maxLength);

        // 打印树
        System.out.println("树");
        for (int i = 0; i < tree.length; i++) {
            // 打印空格，规范格式
            for (int t = 0; t < tree.length - i; t++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= tree[i][0]; j++) {
                System.out.print(tree[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println();

        // 遍历结果
        System.out.println("前序遍历");
        PreOrderTraversal pre = new PreOrderTraversal();
        System.out.print("递归：");
        pre.preOrderRecursion(root);
        System.out.println();
        System.out.print("非递归：");
        pre.preOrder(root);

        System.out.println("中序遍历");
        SequentialTraversal seq = new SequentialTraversal();
        System.out.print("递归：");
        seq.sequentialRecursion(root);
        System.out.println();
        System.out.print("非递归：");
        seq.sequential(root);

        System.out.println("后序遍历");
        PostOrderTraversal post = new PostOrderTraversal();
        System.out.print("递归：");
        post.postOrderRecursion(root);
        System.out.println();
        System.out.print("非递归：");
        post.postOrder(root);

    }

    /**
     * 随机递归生成满二叉树
     * @param root 根节点
     * @param length 树现在深度
     * @param maxLength 最大深度
     */
    private static boolean makeTree(TreeNode root, int length, int maxLength) {

        if(++length == maxLength) {
            return false;
        }
        root.left = new TreeNode(random.nextInt(length * 10));
        root.right = new TreeNode(random.nextInt(length * 10));
        tree[length][++tree[length][0]] = root.left.val;
        tree[length][++tree[length][0]] = root.right.val;

        makeTree(root.left, length, maxLength);
        makeTree(root.right, length, maxLength);
        return true;
    }
}

