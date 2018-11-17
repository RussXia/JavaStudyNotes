package com.xzy.algorithm.binarytree;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 树节点
 *
 * @author RuzzZZ
 * @since 07/03/2018 11:52 AM
 */
@Data
@NoArgsConstructor
public class TreeNode {

    /**
     * 值
     */
    private int value;

    /**
     * 左节点
     */
    private TreeNode left;

    /**
     * 右节点
     */
    private TreeNode right;

    /**
     * 构造函数
     * @param value 二叉树的值
     */
    public TreeNode(int value) {
        super();
        this.value = value;
    }

    /**
     * 向当前二叉树添加一个左子节点
     * @param leftNode 需要添加的左子节点
     * @return 添加的左子节点
     */
    public TreeNode addLeftNode(TreeNode leftNode) {
        this.left = leftNode;
        return leftNode;
    }

    /**
     * 向当前二叉树添加一个右子节点
     * @param rightNode 需要添加的右子节点
     * @return 添加的右子节点
     */
    public TreeNode addRightNode(TreeNode rightNode) {
        this.right = rightNode;
        return rightNode;
    }

    /**
     * 递归获取当前树的深度
     * @param treeNode
     * @return
     */
    public int getTreeDepth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int left = getTreeDepth(treeNode.left);
        int right = getTreeDepth(treeNode.right);
        return left > right ? left + 1 : right + 1;
    }

    /**
     * 向二叉树的最左子节点添加节点
     *
     * @param treeNode    二叉树
     * @param leftSonNode 要添加的最左子节点
     */
    public static void addTreeLeftNode(TreeNode treeNode, TreeNode leftSonNode) {
        while (treeNode.getLeft() != null) {
            treeNode = treeNode.getLeft();
        }
        treeNode.addLeftNode(leftSonNode);
    }

    /**
     * 向二叉树的最右子节点添加节点
     *
     * @param treeNode     二叉树
     * @param rightSonNode 要添加的最右子节点
     */
    public static void addTreeRightNode(TreeNode treeNode, TreeNode rightSonNode) {
        while (treeNode.getRight() != null) {
            treeNode = treeNode.getRight();
        }
        treeNode.addRightNode(rightSonNode);
    }

    /**
     *                       17
     *              13                15
     *          1        2        9        8
     *       4    6   11   9   8   6   13   11
     */
    public static TreeNode mockTreeNode() {
        TreeNode treeNode = new TreeNode(17);
        TreeNode leftNode = treeNode.addLeftNode(new TreeNode(13));
        {
            TreeNode leftNode1 = leftNode.addLeftNode(new TreeNode(1));
            {
                TreeNode leftNode2 = leftNode1.addLeftNode(new TreeNode(4));
                TreeNode rightNode2 = leftNode1.addRightNode(new TreeNode(6));
            }
            TreeNode rightNode1 = leftNode.addRightNode(new TreeNode(2));
            {
                TreeNode leftNode2 = rightNode1.addLeftNode(new TreeNode(11));
                TreeNode rightNode2 = rightNode1.addRightNode(new TreeNode(9));
            }
        }
        TreeNode rightNode = treeNode.addRightNode(new TreeNode(15));
        {
            TreeNode leftNode1 = rightNode.addLeftNode(new TreeNode(9));
            {
                TreeNode leftNode2 = leftNode1.addLeftNode(new TreeNode(8));
                TreeNode rightNode2 = leftNode1.addRightNode(new TreeNode(6));
            }
            TreeNode rightNode1 = rightNode.addRightNode(new TreeNode(8));
            {
                TreeNode leftNode2 = rightNode1.addLeftNode(new TreeNode(13));
                TreeNode rightNode2 = rightNode1.addRightNode(new TreeNode(11));
            }
        }
        return treeNode;
    }
}