package recursion;

import binaryTree.printTree.TreeNode;
import linkedList.ReverseLinkedList;
import util.BaseUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 给一个整形集合，转换成链表
 *
 * @author: xuyh
 * @create: 2020/5/5
 **/
public class LinkedListCreator {
    public static TreeNode createLinkedList(List<Integer> values) {
        if (values == null || values.size() == 0) {
            return null;
        }
        TreeNode head = new TreeNode(values.get(0));
        head.next = createLinkedList(values.subList(1, values.size()));
        return head;
    }

    public static TreeNode createLargeLinkedList(int size) {
        TreeNode pre = null;
        TreeNode head = null;
        for (int i = 0; i < size; i++) {
            TreeNode node = new TreeNode(i);
            if (pre != null) {
                pre.next = node;
            } else {
                head = node;
            }
            pre = node;
        }
        return head;
    }

    public static void main(String[] args) {
        List<Integer> v1 = Arrays.asList();
        List<Integer> v2 = Arrays.asList(1);
        List<Integer> v3 = Arrays.asList(1, 2, 3, 4, 5);
        BaseUtil.printLinkedList(createLinkedList(v1));
        BaseUtil.printLinkedList(createLinkedList(v2));
        BaseUtil.printLinkedList(createLinkedList(v3));
//        ReverseLinkedList.reverseLinkedList(createLargeLinkedList(100000));
        BaseUtil.printLinkedList(ReverseLinkedList.reverseList(createLargeLinkedList(100000)));
    }
}
