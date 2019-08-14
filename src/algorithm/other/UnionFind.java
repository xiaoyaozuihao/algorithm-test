package other;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 * @author xuyh
 * @date 2019/5/21
 */
public class UnionFind {
    public static void main(String[] args) {
        byte[] bype=new byte[1000*1024*1024];
        System.out.println("最大内存："+Runtime.getRuntime().maxMemory()/1024/1024+"M");
        System.out.println("可用内存："+Runtime.getRuntime().freeMemory()/1024/1024+"M");
        System.out.println("已使用内存："+Runtime.getRuntime().totalMemory()/1024/1024+"M");
    }
    public static class Node{
       //whatever you like
    }
    public static class UnionFindSet{
        public HashMap<Node,Node> fatherMap;
        public HashMap<Node,Integer> sizeMap;
        public UnionFindSet(){
            fatherMap=new HashMap<>();
            sizeMap=new HashMap<>();
        }

        public void makeSets(List<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node findHead(Node node){
            //迭代解法
//            Stack<Node> stack=new Stack<>();
//            Node father = fatherMap.get(node);
//            Node cur=node;
//            while(cur!=father){
//                stack.push(cur);
//                cur=father;
//                father=fatherMap.get(cur);
//            }
//            while(!stack.isEmpty()){
//                fatherMap.put(stack.pop(),father);
//            }
//            return father;
            //简洁写法
            Stack<Node> stack=new Stack<>();
            while(node!=fatherMap.get(node)){
                 stack.push(node);
                 node=fatherMap.get(node);
            }
            while(!stack.isEmpty()){
                fatherMap.put(stack.pop(),node);
            }
            return node;
            //递归解法
//            Node fatherNode = fatherMap.get(node);
//            if(fatherNode!=node){
//                fatherNode=findHead(fatherNode);
//            }
//            fatherMap.put(node,fatherNode);
//            return fatherNode;
        }

        public boolean isSameSet(Node node1,Node node2){
            return findHead(node1)==findHead(node2);
        }

        public void union(Node a,Node b){
            if(a==null||b==null){
                return;
            }
            Node aHead=findHead(a);
            Node bHead=findHead(b);
            if(aHead!=bHead){
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                if(aSize<=bSize){
                    fatherMap.put(aHead,bHead);
                    sizeMap.put(bHead,aSize+bSize);
                }else{
                    fatherMap.put(bHead,aHead);
                    sizeMap.put(aHead,aSize+bSize);
                }
            }
        }
    }
}
