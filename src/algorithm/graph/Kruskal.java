package graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树K算法
 * @author xuyh
 * @date 2019/5/31
 */
public class Kruskal {
    public static class UnionFind{
        private HashMap<Node,Node> fatherMap;
        private HashMap<Node,Integer> rankMap;
        public UnionFind(){
            fatherMap=new HashMap<>();
            rankMap=new HashMap<>();
        }
        private Node findFather(Node node){
            Node fatherNode = fatherMap.get(node);
            if(fatherNode!=node){
                fatherNode=findFather(fatherNode);
            }
            fatherMap.put(node,fatherNode);
            return fatherNode;
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            rankMap.clear();
            for(Node node : nodes){
                fatherMap.put(node,node);
                rankMap.put(node,1);
            }
        }

        public boolean isSameSet(Node n1,Node n2){
            return findFather(n1)==findFather(n2);
        }

        public void union(Node n1,Node n2){
            Node f1=findFather(n1);
            Node f2=findFather(n2);
            if(f1!=f2){
                Integer l1 = rankMap.get(n1);
                Integer l2=rankMap.get(n2);
                if(l1<=l2){
                    fatherMap.put(f1,f2);
                    rankMap.put(f2,l1+l2);
                }else{
                    fatherMap.put(f2,f1);
                    rankMap.put(f1,l1+l2);
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
}
