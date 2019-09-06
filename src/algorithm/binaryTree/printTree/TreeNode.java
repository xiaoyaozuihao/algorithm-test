package binaryTree.printTree;

import binaryTree.printTree.interfaces.BinaryTreeInfo;

/**
 * 适用于树，链表,随机指针等结构
 * @author xuyh
 * @date 2019/5/11
 */
public class TreeNode implements BinaryTreeInfo {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public TreeNode next;
    public TreeNode random;
    public TreeNode(int data){
        val =data;
    }

    @Override
    public Object root() {
        return this;
    }

    @Override
    public Object left(Object node) {
        return ((TreeNode)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((TreeNode)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((TreeNode)node).val;
    }
}
