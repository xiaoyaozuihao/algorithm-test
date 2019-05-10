package binaryTree.printTree;

/**
 * @author xuyh
 * @date 2019/5/10
 */
public interface BinaryTreeInfo {
    /**
     * who is the root node
     */
    Object root();
    /**
     * how to get the left child of the node
     */
    Object left(Object node);
    /**
     * how to get the right child of the node
     */
    Object right(Object node);
    /**
     * how to print the node
     */
    Object string(Object node);
}
