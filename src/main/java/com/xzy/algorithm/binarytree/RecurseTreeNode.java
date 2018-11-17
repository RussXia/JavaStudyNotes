package com.xzy.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 递归遍历二叉树
 * 前序:根节点、左节点、右节点
 * 中序:左节点、根节点、右节点
 * 后序:左节点、右节点、根节点
 *
 * @author RuzzZZ
 * @since 07/03/2018 11:56 AM
 */
public class RecurseTreeNode {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.mockTreeNode();

        //前序遍历:17,13,1,4,6,2,11,9,15,9,8,6,8,13,11
        recurseFront(treeNode);
        System.out.println();

        //非递归前序遍历
        List<Integer> result1 = traverseTreeNodeFront(treeNode);
        System.out.println(result1);


        //中序遍历:4,1,6,13,11,2,9,17,8,9,6,15,13,8,11
        recurseMid(treeNode);
        System.out.println();

        //非递归中序遍历
        List<Integer> result2 = traverseTreeNodeMid(treeNode);
        System.out.println(result2);

        //后序遍历:4,6,1,11,9,2,13,8,6,9,13,11,8,15,17
        recurseEnd(treeNode);
        System.out.println();

    }

    /**
     * 非递归方式前序遍历二叉树
     * @param treeNode  需要遍历的二叉树
     * @return  遍历出来的list结果集
     */
    public static List<Integer> traverseTreeNodeFront(TreeNode treeNode) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(treeNode == null){
            return list;
        }
        //利用压栈出栈前序遍历二叉树
        stack.push(treeNode);
        while(!stack.empty()){
            treeNode = stack.pop();
            if (treeNode != null) {
                list.add(treeNode.getValue());
                //因为栈后进先出(LIFO)，所以先压入右节点，后压入左节点
                stack.push(treeNode.getRight());
                stack.push(treeNode.getLeft());
            }
        }
        return list;
    }

    /**
     * 前序遍历
     *
     * @param treeNode 需要遍历的二叉树
     */
    public static void recurseFront(TreeNode treeNode) {
        if (treeNode == null)
            return;
        System.out.print(treeNode.getValue() + "\t");
        recurseFront(treeNode.getLeft());
        recurseFront(treeNode.getRight());
    }

    /**
     * 非递归方式中序遍历二叉树
     * @param treeNode 需要遍历的二叉树
     * @return 遍历出来的list结果集
     */
    public static List<Integer> traverseTreeNodeMid(TreeNode treeNode) {
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = treeNode;

        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.getLeft();
            }
            cur = stack.pop();
            list.add(cur.getValue());
            cur = cur.getRight();
        }
        return list;
    }

    /**
     * 中序遍历
     *
     * @param treeNode 需要遍历的二叉树
     */
    public static void recurseMid(TreeNode treeNode) {
        if (treeNode == null)
            return;
        recurseMid(treeNode.getLeft());
        System.out.print(treeNode.getValue() + "\t");
        recurseMid(treeNode.getRight());
    }

    /**
     * 后序遍历
     *
     * @param treeNode 需要遍历的二叉树
     */
    public static void recurseEnd(TreeNode treeNode) {
        if (treeNode == null)
            return;
        recurseEnd(treeNode.getLeft());
        recurseEnd(treeNode.getRight());
        System.out.print(treeNode.getValue() + "\t");
    }
}