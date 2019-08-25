package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {

    public static void main(String[] args) {
    }

    static class UnionFind{
        static class Node{

        }
        private Map<Node,Node> fatherMap;
        private Map<Node,Integer> sizeMap;
        public UnionFind(List<Node> list){
            fatherMap=new HashMap<>();
            sizeMap=new HashMap<>();
            for (Node node : list) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }
        public void union(Node n1,Node n2){
            if(n1==null||n2==null){
                return;
            }
            Node node1 = findHead(n1);
            Node node2 = findHead(n2);
            if(node1!=node2){
                Integer size1 = sizeMap.get(node1);
                Integer size2 = sizeMap.get(node2);
                if(size1<=size2){
                    fatherMap.put(node1,node2);
                    sizeMap.put(node2,size1+size2);
                }else{
                    fatherMap.put(node2,node1);
                    sizeMap.put(node1,size1+size2);
                }
            }
        }

        public boolean isSameSet(Node n1,Node n2){
            return findHead(n1)==findHead(n2);
        }
        public Node findHead(Node node){
//            Stack<Node> stack=new Stack<>();
//            while (fatherMap.get(node) != node) {
//                stack.push(node);
//                node=fatherMap.get(node);
//            }
//            while(!stack.isEmpty()){
//                fatherMap.put(stack.pop(),node);
//            }
//            return node;
            Node fatherNode = fatherMap.get(node);
            if(fatherNode!=node){
                fatherNode=findHead(fatherNode);
            }
            fatherMap.put(node,fatherNode);
            return fatherNode;
        }
    }
}

