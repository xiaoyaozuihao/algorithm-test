package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * n叉树的层序遍历
 * @author xuyh
 * @date 2019/5/23
 */
public class NTreeLevelOrder {
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }
    public static void dfs(Node root,int depth,List<List<Integer>> result){
        if(root!=null){
            if(result.size()<depth+1){
                result.add(depth,new ArrayList<>());
            }
            result.get(depth).add(root.value);
            depth++;
            for(Node child:root.nexts){
                dfs(child,depth,result);
            }
        }

    }

    public static List<List<Integer>> levelOrder1(Node node){
        if(node==null){
            return new ArrayList<>();
        }
        List<List<Integer>> res=new ArrayList<>();
        Queue<Node> queue=new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            List<Integer> list=new ArrayList<>();
            int size=queue.size();
            while(size-->0){
                node=queue.poll();
                list.add(node.value);
                queue.addAll(node.nexts);
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
