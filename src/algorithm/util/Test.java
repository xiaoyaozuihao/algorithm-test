package util;

import binaryTree.printTree.TreeNode;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        String str = "abcccdefwg";
        String exp = "ab.*d.*e.*g";
    }

    public static TreeNode addTwoNum(TreeNode n1,TreeNode n2){
        TreeNode dummyNode=new TreeNode(0);
        TreeNode p=n1,q=n2, cur=dummyNode;
        int carry=0,sum=0,x=0,y=0;
        while(p!=null||q!=null){
            if(p!=null){
                x=p.val;
                p=p.next;
            }
            if(q!=null){
                y=q.val;
                q=q.next;
            }
            sum=x+y+carry;
            carry=sum/10;
            cur.next=new TreeNode(sum%10);
            cur=cur.next;
        }
        if(carry>0){
            cur.next=new TreeNode(carry);
        }
        return dummyNode.next;
    }

    public static TreeNode dfs(TreeNode n1,TreeNode n2,int carry){
        if(n1==null&&n2==null&&carry==0){
            return null;
        }
        int sum=(n1!=null?n1.val:0)+(n2!=null?n2.val:0)+carry;
        TreeNode list=new TreeNode(sum%10);
        list.next=dfs(n1!=null?n1.next:null,n2!=null?n2.next:null,sum/10);
        return list;
    }
}

