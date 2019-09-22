package binaryTree;

import binaryTree.printTree.TreeNode;
import binaryTree.printTree.utils.BinaryTreesUtil;

/**
 * morris遍历，二叉树的神级遍历方式
 *
 * @author: xuyh
 * @create: 2019/9/22
 **/
public class MorrisTraversal {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);
        BinaryTreesUtil.println(node);
        morrisIn(node);
        System.out.println();
        morrisPre(node);
        System.out.println();
        morrisPos(node);
    }

    public static void morrisPre(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode cur = node;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.val + " ");//第一次来到这个结点时打印
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;
            } else {//没有左子树，也是第一次来到这个结点
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
    }

    public static void morrisIn(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode cur = node;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;
            }
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }

    //后序稍微麻烦，因为morris序只能来到一个结点两次，所以可以巧妙利用第二次来到这个结点时，逆序打印它的左子树即可实现后序遍历。
    public static void morrisPos(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode mostRight = null;
        TreeNode cur = node;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;
                //逆序打印当前结点的左子树
                printEdge(cur.left);
            }
            cur = cur.right;
        }
        //最后逆序打印整棵树的右子树
        printEdge(node);
    }

    private static void printEdge(TreeNode node) {
        TreeNode tail = reverseNode(node);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseNode(tail);
    }

    public static TreeNode reverseNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode pre = null;
        TreeNode next;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    //morris序，所谓的先序中序后序只是在morris序的基础上改造而成
    public static void morrisOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode cur = node;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            //左孩子不为空，找到左孩子的最右结点,且最右结点不等于当前结点
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //如果最右结点为空，说明是第一次来到这个结点，让这个结点指向cur,cur向左走。
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                //第二次来到这个结点，让这个结点重新指向null
                mostRight.right = null;
            }
            cur = cur.right;
        }
    }
}
