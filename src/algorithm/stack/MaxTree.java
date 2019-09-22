package stack;

import binaryTree.printTree.TreeNode;
import binaryTree.printTree.utils.BinaryTreesUtil;
import util.BaseUtil;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个数组，构造数组的maxTree，数组中没有重复值
 * 要求包括maxTree在内且在其中的任何一个子树，值最大的结点均是树的头结点。
 * 如果数组长度为n，要求时间复杂度O(n)
 * 解法1：可以将数组建成大根堆，将每个值建成树的结点，依次连接即可。更容易理解，而且满足时间复杂度
 * 解法2：单调栈解法
 *
 * @author xuyh
 * @date 2019/9/20
 */
public class MaxTree {
    //大根堆的解法
    public static TreeNode getMaxTreeByHeap(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        buildMaxHeap(arr);
//        for (int i = 1; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        TreeNode[] nodes = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new TreeNode(arr[i]);
        }
        TreeNode head = nodes[0];
        for (int i = 0; i < nodes.length; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            nodes[i].left = left < nodes.length ? nodes[left] : null;
            nodes[i].right = right < nodes.length ? nodes[right] : null;
        }
        return head;
    }

    private static void heapInsert(int[] arr, int i) {
        while (arr[(i - 1) / 2] < arr[i]) {
            BaseUtil.swap(arr, (i - 1) / 2, i);
            i = (i - 1) / 2;
        }
    }

    //时间复杂度估算，倒数第二层的结点最多向下调整一次，倒数第三次调整两次，依次类推，头结点调整logN次。
    //假如数的高度为k=logN,每层的节点数为2^(k-1),所以有2^(k-2)*1+2^(k-3)*2+2^(k-4)*3+...+1*logN,数学上可以证明时间复杂度趋近O(N)
    private static void buildMaxHeap(int[] arr) {
        //从底向上建立大根堆，从最后一个元素的父结点开始（(arr.length-1-1)/2），一直到头结点
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            int temp = arr[i];
            int j = 2 * i + 1;
            while (j < arr.length) {
                if (j + 1 < arr.length && arr[j] < arr[j + 1]) {
                    j++;
                }
                if (temp >= arr[j]) {
                    break;
                }
                arr[i] = arr[j];
                i = j;
                j = 2 * i + 1;
            }
            arr[i] = temp;
        }
    }

    //单调栈解法，将数组变成结点数组，找个每个结点左边离他最近的和右边离他最近的结点，
    //如果某个结点无左右离他最近的结点，那么他就是整颗树的头结点
    //如果某个结点左右只有一侧有离他最近的结点，那么这个结点就作为离他最近的结点的子节点
    //如果某个结点左右两侧均有离他最近的结点，那么这个结点就选择左右值中较小的结点，成为他的子节点。
    //可以画图证明，这种方法一定会形成一颗二叉树，而不是多叉树，也不会形成森林。
    // （整个数组没有重复值，最大值作为整棵树的头结点，其余结点都会找比自己值大的结点作为父节点，每个节点都有归属，所以不会形成森林）
    // （证明只能形成二叉树，只需要证明某个结点左右两边最多只存在一个子结点即可，利用反证法，假如a结点有b和c两个结点作为他的右孩子，
    // 那么一定有a>b>c,然后对b和c进行分情况讨论，最终都会推出矛盾。）
    public static TreeNode getMaxTree(int[] arr) {
        TreeNode[] nArr = new TreeNode[arr.length];
        for (int i = 0; i != arr.length; i++) {
            nArr[i] = new TreeNode(arr[i]);
        }
        //准备一个单调栈
        Stack<TreeNode> stack = new Stack<>();
        //准备两个map，一个存放当前结点左边离他最近的比他大的结点，一个是右边的
        HashMap<TreeNode, TreeNode> lBigMap = new HashMap<>();
        HashMap<TreeNode, TreeNode> rBigMap = new HashMap<>();
        //正序遍历，找出左边大的
        for (int i = 0; i != nArr.length; i++) {
            TreeNode curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().val < curNode.val) {
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curNode);
        }
        //对栈中剩余的元素进行处理
        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap);
        }
        //逆序遍历，找出右边大的
        for (int i = nArr.length - 1; i != -1; i--) {
            TreeNode curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().val < curNode.val) {
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);
        }
        //串联整棵树
        TreeNode head = null;
        for (int i = 0; i != nArr.length; i++) {
            TreeNode curNode = nArr[i];
            TreeNode left = lBigMap.get(curNode);
            TreeNode right = rBigMap.get(curNode);
            //左右离他最近最大的都没有，则它是头结点
            if (left == null && right == null) {
                head = curNode;
                //只有一侧有最大的，那就成为那个结点的子结点，需要判断那个结点的子结点哪一侧为空，就挂在哪一侧
            } else if (left == null) {
                linkTree(right, curNode);
            } else if (right == null) {
                linkTree(left, curNode);
            } else {
                //左右都有最近最大的结点，那就取两者中值最小的结点，成为其子节点
                TreeNode parent = left.val < right.val ? left : right;
                linkTree(parent, curNode);
            }
        }
        return head;
    }

    public static void popStackSetMap(Stack<TreeNode> stack, HashMap<TreeNode, TreeNode> map) {
        TreeNode popNode = stack.pop();
        if (stack.isEmpty()) {
            map.put(popNode, null);
        } else {
            map.put(popNode, stack.peek());
        }
    }

    //更简洁的实现
    //在遍历数组的过程中同时确定左边和右边临近的最大值
    public static TreeNode getMaxTree1(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> lmax = new HashMap<>();
        HashMap<Integer, Integer> rmax = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                //谁让他弹出谁就是他右边临近最大
                int index = stack.pop();
                rmax.put(index, i);
                //谁在他下面谁就是他左边临近最大，没有就是空
                lmax.put(index, stack.isEmpty() ? null : stack.peek());
            }
            stack.push(i);
        }
        //处理栈中剩余元素
        while (!stack.isEmpty()) {
            int index = stack.pop();
            rmax.put(index, null);
            lmax.put(index, stack.isEmpty() ? null : stack.peek());
        }
        //生成结点数组，以便下面进行结点连接
        TreeNode[] nodes = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new TreeNode(arr[i]);
        }
        TreeNode head = null;
        for (int i = 0; i < nodes.length; i++) {
            Integer lmaxIndex = lmax.get(i);
            Integer rmaxIndex = rmax.get(i);
            if (lmaxIndex == null && rmaxIndex == null) {
                head = nodes[i];
            } else if (lmaxIndex == null) {
                linkTree(nodes[rmaxIndex], nodes[i]);
            } else if (rmaxIndex == null) {
                linkTree(nodes[lmaxIndex], nodes[i]);
            } else {
                TreeNode parent = arr[lmaxIndex] < arr[rmaxIndex] ? nodes[lmaxIndex] : nodes[rmaxIndex];
                linkTree(parent, nodes[i]);
            }
        }
        return head;
    }

    private static void linkTree(TreeNode node, TreeNode cur) {
        if (node.left == null) {
            node.left = cur;
        } else {
            node.right = cur;
        }
    }

    public static void main(String[] args) {
        int[] uniqueArr = {3, 4, 5, 1, 2, 9, 6, 7, 8};
        TreeNode head = getMaxTree(uniqueArr);
        BinaryTreesUtil.println(head);
        TreeNode head1 = getMaxTree1(uniqueArr);
        BinaryTreesUtil.println(head1);
        TreeNode head2 = getMaxTreeByHeap(uniqueArr);
        BinaryTreesUtil.println(head2);
    }
}
