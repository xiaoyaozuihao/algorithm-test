package binaryTree;

/**
 * 在二叉树中找到一个节点的后继节点，每个节点都有一个父节点的指针
 * 在二叉树的中序遍历的序列中，node的下一个节点叫作node的后继节点。
 * @author xuyh
 * @date 2019/5/10
 */
public class SuccessorNode {
    public static TreeNode getSuccessorNode(TreeNode node){
        if(node==null){
            return null;
        }
        if(node.right!=null){
            return getLeftMost(node.right);
        }else{
            TreeNode parent=node.parent;
            while(parent!=null&&node!=parent.left){
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }

    private static TreeNode getLeftMost(TreeNode node) {
        if(node ==null){
            return null;
        }
        while(node.left!=null){
            node=node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(6);
        head.parent = null;
        head.left = new TreeNode(3);
        head.left.parent = head;
        head.left.left = new TreeNode(1);
        head.left.left.parent = head.left;
        head.left.left.right = new TreeNode(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new TreeNode(4);
        head.left.right.parent = head.left;
        head.left.right.right = new TreeNode(5);
        head.left.right.right.parent = head.left.right;
        head.right = new TreeNode(9);
        head.right.parent = head;
        head.right.left = new TreeNode(8);
        head.right.left.parent = head.right;
        head.right.left.left = new TreeNode(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new TreeNode(10);
        head.right.right.parent = head.right;

        TreeNode test = head.left.left;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.left.left.right;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.left;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.left.right;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.left.right.right;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.right.left.left;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.right.left;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.right;
        System.out.println(test.val + " next: " + getSuccessorNode(test).val);
        test = head.right.right; // 10's next is null
        System.out.println(test.val + " next: " + getSuccessorNode(test));
    }
}
