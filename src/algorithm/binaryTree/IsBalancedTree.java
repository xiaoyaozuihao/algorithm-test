package binaryTree;

/**
 * 判断二叉树是否是平衡二叉树
 * @author xuyh
 * @date 2019/5/12
 */
public class IsBalancedTree {
    public static class ReturnData{
        private boolean isB;
        private int h;
        public ReturnData(boolean isB,int h){
            this.isB=isB;
            this.h=h;
        }
    }
    public static boolean isBalancedTree(Node node){
        return process(node).isB;
    }

    public static boolean isBalance(Node node){
        boolean[] res=new boolean[1];
        res[0]=true;
        getHeight(node,1,res);
        return res[0];
    }

    private static int getHeight(Node node, int level, boolean[] res) {
        if(node==null){
            return level;
        }
        int hl=getHeight(node.left,level+1,res);
        if(!res[0]){
            return level;
        }
        int hr=getHeight(node.right,level+1,res);
        if(!res[0]){
            return level;
        }
        if(Math.abs(hl-hr)>1){
            res[0]=false;
        }
        return Math.max(hl,hr);

    }

    public static ReturnData process(Node node){
        if(node==null){
            return new ReturnData(true,0);
        }
        ReturnData left = process(node.left);
        if(!left.isB){
            return new ReturnData(false,0);
        }
        ReturnData right = process(node.right);
        if(!right.isB){
            return new ReturnData(false,0);
        }
        if(Math.abs(left.h-right.h)>1){
            return new ReturnData(false,0);
        }
        return new ReturnData(true,Math.max(left.h,right.h)+1);
    }

    public static boolean isBalancedTree1(Node head){
        return process1(head)!=-1;
    }
    public static int process1(Node head){
        if(head==null){
            return 1;
        }
        int hl=process1(head.left);
        if(hl==-1){
            return -1;
        }
        int hr=process1(head.right);
        if(hr==-1){
            return -1;
        }
        if(Math.abs(hl-hr)>1){
            return -1;
        }
        return Math.min(hl,hr)+1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalancedTree(head));
        System.out.println(isBalance(head));
        System.out.println(isBalancedTree1(head));
    }
}
