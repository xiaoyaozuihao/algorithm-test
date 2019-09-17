package array;

import binaryTree.printTree.TreeNode;
import binaryTree.printTree.utils.BinaryTreesUtil;

/**
 * T1树中是否包含T2子树，所谓子树就是某个分支下面的所有，不能只取一部分
 * ┌─1─┐
 * │   │
 * ┌─2─┐ 3        ┌─2─┐            ┌─2
 * │   │          │   │            │
 * 4   5          4   5  是子树    4    不是子树
 *
 * @author: xuyh
 * @create: 2019/9/17
 **/
public class KMP_T1SubtreeEqualsT2 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        BinaryTreesUtil.println(node);
        TreeNode node1 = new TreeNode(2);
        node1.left = new TreeNode(4);
        node1.right = new TreeNode(5);
        BinaryTreesUtil.println(node1);
        System.out.println(isSubTree(node, node1));
    }

    public static boolean isSubTree(TreeNode node1, TreeNode node2) {
        String str1 = serialByPre(node1);
        String str2 = serialByPre(node2);
        return getIndexOf(str1, str2) != -1;
    }

    private static int getIndexOf(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return -1;
        }
        int s1 = 0, s2 = 0;
        int[] next = nextArray(str2);
        while (s1 < str1.length() && s2 < str2.length()) {
            if (str1.charAt(s1) == str2.charAt(s2)) {
                s1++;
                s2++;
            } else if (next[s2] == -1) {
                s1++;
            } else {
                s2 = next[s2];
            }
        }
        return s2 == str2.length() ? s1 - s2 : -1;
    }

    public static int[] nextArray(String str) {
        if (str.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length()];
        next[0] = -1;
        next[1] = 0;
        int cur = 0;
        int index = 2;
        while (index < next.length) {
            if (str.charAt(index - 1) == str.charAt(cur)) {
                next[index++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }

    private static String serialByPre(TreeNode node) {
        if (node == null) {
            return "#!";
        }
        String res = node.val + "!";
        res += serialByPre(node.left);
        res += serialByPre(node.right);
        return res;
    }
}
