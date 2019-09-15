package graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * 最小生成树K算法
 * @author xuyh
 * @date 2019/5/31
 */
public class Kruskal {
    public static class UnionFind{
        private HashMap<Node,Node> fatherMap;
        private HashMap<Node,Integer> sizeMap;
        public UnionFind(){
            fatherMap=new HashMap<>();
            sizeMap =new HashMap<>();
        }
        private Node findFather(Node node){
//            Node fatherNode = fatherMap.get(node);
//            if(fatherNode!=node){
//                fatherNode=findFather(fatherNode);
//            }
//            fatherMap.put(node,fatherNode);
//            return fatherNode;
            Stack<Node> stack=new Stack<>();
            while(node!=fatherMap.get(node)){
                stack.push(node);
                node=fatherMap.get(node);
            }
            while(!stack.isEmpty()){
                fatherMap.put(stack.pop(),node);
            }
            return node;
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for(Node node : nodes){
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public boolean isSameSet(Node n1,Node n2){
            return findFather(n1)==findFather(n2);
        }

        public void union(Node n1,Node n2){
            Node f1=findFather(n1);
            Node f2=findFather(n2);
            if(f1!=f2){
                Integer l1 = sizeMap.get(n1);
                Integer l2= sizeMap.get(n2);
                if(l1<=l2){
                    fatherMap.put(f1,f2);
                    sizeMap.put(f2,l1+l2);
                }else{
                    fatherMap.put(f2,f1);
                    sizeMap.put(f1,l1+l2);
                }
            }
        }
    }

    public static Set<Edge> kruskal(Graph graph){
        UnionFind unionFind=new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> edges=new PriorityQueue<>(Comparator.comparing(e->e.weight));
        for(Edge edge:graph.edges){
            edges.add(edge);
        }
        Set<Edge> res=new HashSet<>();
        while(!edges.isEmpty()){
            Edge cur = edges.poll();
            if(!unionFind.isSameSet(cur.from,cur.to)){
                res.add(cur);
                unionFind.union(cur.from,cur.to);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[][] arr = {
                {4, 1, 2},
                {2, 1, 3},
                {5, 4, 3},
                {1, 4, 5},
                {1, 5, 3},
                {1, 3, 2},
        };
        Graph graph = GraphGenerator.generateGraph(arr);
        Set<Edge> kruskal = kruskal(graph);
        kruskal.stream().map(e->e.weight).forEach(s->System.out.print(s+" "));
    }
}
