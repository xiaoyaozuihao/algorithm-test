package linkedList;

import binaryTree.TreeNode;

/**
 * 两个单链表相交的一系列问题
 * @author xuyh
 * @date 2019/5/3
 */
public class FindFirstIntersectNode {
    public static TreeNode getIntersectNode(TreeNode head1, TreeNode head2){
        if(head1==null||head2==null){
            return null;
        }
        TreeNode loop1 = getLoopNode(head1);
        TreeNode loop2 = getLoopNode(head2);
        if(loop1==null&&loop1==null){//两个无环链表的相交问题
            return noLoop(head1,head2);
        }
        if(loop1!=null&&loop2!=null){//两个有环链表的相交问题
            return bothLoop(head1,loop1,head2,loop2);
        }
        //一个有环，一个无环，一定不相交
        return null;
    }

    private static TreeNode bothLoop(TreeNode head1, TreeNode loop1, TreeNode head2, TreeNode loop2) {
        TreeNode cur1;
        TreeNode cur2;
        if(loop1==loop2){
            cur1=head1;
            cur2=head2;
            int n=0;
            while(cur1!=loop1){
                n++;
                cur1=cur1.next;
            }
            while(cur2!=loop2){
                n--;
                cur2=cur2.next;
            }
            cur1=n>0?head1:head2;
            cur2=n>0?head2:head1;
            n= Math.abs(n);
            while(n!=0){
                n--;
                cur1=cur1.next;
            }
            while(cur1!=cur2){
                cur1=cur1.next;
                cur2=cur2.next;
            }
            return cur1;
        }else{
            cur1=loop1.next;
            while(cur1!=loop1){
                if(cur1==loop2){
                    return loop1;
                }
                cur1=cur1.next;
            }
            return null;
        }
    }

    private static TreeNode noLoop(TreeNode head1, TreeNode head2) {
        TreeNode cur1=head1;
        TreeNode cur2=head2;
        int n=0;
        //分别遍历两个链表，找到他们各自的最后一个节点，如果不相等，一定不相交。
        while(cur1.next!=null){
            n++;
            cur1=cur1.next;
        }
        while(cur2.next!=null){
            n--;
            cur2=cur2.next;
        }
        if(cur1!=cur2){
            return null;
        }
        //如果相交，让长的链表先走两个链表的长度差，然后两个一起走。
        cur1=n>0?head1:head2;
        cur2=cur1==head1?head2:head1;
        n = Math.abs(n);
        while(n!=0){
            n--;
            cur1=cur1.next;
        }
        while(cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }

    public static TreeNode getLoopNode(TreeNode head){
        //三个以上节点的链表才可能构成环
        if(head==null||head.next==null||head.next.next==null){
            return null;
        }
        TreeNode n1=head.next;//slow
        TreeNode n2=head.next.next;//fast
        while(n1!=n2){
            if(n2.next==null||n2.next.next==null){
                return null;
            }
            n2=n2.next.next;
            n1=n1.next;
        }
        n2=head;
        while(n1!=n2){
            n1=n1.next;
            n2=n2.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        TreeNode head1 = new TreeNode(1);
        head1.next = new TreeNode(2);
        head1.next.next = new TreeNode(3);
        head1.next.next.next = new TreeNode(4);
        head1.next.next.next.next = new TreeNode(5);
        head1.next.next.next.next.next = new TreeNode(6);
        head1.next.next.next.next.next.next = new TreeNode(7);

        // 0->9->8->6->7->null
        TreeNode head2 = new TreeNode(0);
        head2.next = new TreeNode(9);
        head2.next.next = new TreeNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new TreeNode(1);
        head1.next = new TreeNode(2);
        head1.next.next = new TreeNode(3);
        head1.next.next.next = new TreeNode(4);
        head1.next.next.next.next = new TreeNode(5);
        head1.next.next.next.next.next = new TreeNode(6);
        head1.next.next.next.next.next.next = new TreeNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new TreeNode(0);
        head2.next = new TreeNode(9);
        head2.next.next = new TreeNode(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new TreeNode(0);
        head2.next = new TreeNode(9);
        head2.next.next = new TreeNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);
    }
}
