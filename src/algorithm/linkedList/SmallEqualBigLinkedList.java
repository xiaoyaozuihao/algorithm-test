package linkedList;

import binaryTree.TreeNode;
import util.BaseUtil;

/**
 * 将单向链表按某值划分为左边小，中间相等，右边大的形式
 *
 * @author xuyh
 * @date 2019/5/2
 */
public class SmallEqualBigLinkedList {

    public static TreeNode listPartition1(TreeNode head, int pivot) {
        if(head==null){
            return head;
        }
        TreeNode cur=head;
        int i=0;
        while(cur!=null){
            i++;
            cur=cur.next;
        }
        TreeNode[] nodes=new TreeNode[i];
        cur=head;
        for(i=0;i!=nodes.length;i++){
            nodes[i]=cur;
            cur=cur.next;
        }
        arrayPartition(nodes,pivot);
        for(i=1;i!=nodes.length;i++){
            nodes[i-1].next=nodes[i];
        }
        nodes[i-1].next=null;
        return nodes[0];
    }

    private static void arrayPartition(TreeNode[] nodes, int pivot) {
        int less = -1;
        int more = nodes.length;
        int index = 0;
        while (index != more) {
            if (nodes[index].val < pivot) {
                swap(nodes, index++, ++less);
            } else if (nodes[index].val > pivot) {
                swap(nodes, index, --more);
            } else {
                index++;
            }
        }
    }

    private static void swap(TreeNode[] nodes, int a, int b) {
        TreeNode temp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = temp;
    }

    public static TreeNode listPartition2(TreeNode head, int pivot) {
        TreeNode sh=null;
        TreeNode st=null;
        TreeNode eh=null;
        TreeNode et=null;
        TreeNode bh=null;
        TreeNode bt=null;
        TreeNode next=null;
        while(head!=null){
            next=head.next;
            head.next=null;
            if(head.val <pivot){
                if(sh==null){
                    sh=head;
                    st=head;
                }else{
                    st.next=head;
                    st=head;
                }
            }else if(head.val ==pivot){
                if(eh==null){
                    eh=head;
                    et=head;
                }else{
                    et.next=head;
                    et=head;
                }
            }else{
                if(bh==null){
                    bh=head;
                    bt=head;
                }else{
                    bt.next=head;
                    bt=head;
                }
            }
            head=next;
        }
        if(st!=null){
            st.next=eh;
            et=et==null?st:et;
        }
        if(et!=null){
            et.next=bh;

        }
        return sh!=null?sh:eh!=null?eh:bh;
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(7);
        head1.next = new TreeNode(9);
        head1.next.next = new TreeNode(1);
        head1.next.next.next = new TreeNode(8);
        head1.next.next.next.next = new TreeNode(5);
        head1.next.next.next.next.next = new TreeNode(2);
        head1.next.next.next.next.next.next = new TreeNode(5);
//        TreeNode node = listPartition1(head1, 3);
//        printLinkedList(node);
        TreeNode node1 = listPartition2(head1, 3);
        BaseUtil.printLinkedList(node1);
    }
}
