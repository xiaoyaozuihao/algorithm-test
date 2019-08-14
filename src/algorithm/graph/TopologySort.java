package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 拓扑排序
 * @author xuyh
 * @date 2019/5/31
 */
public class TopologySort {
    public static List<Node> topologySort(Graph graph){
        Queue<Node> zeroInNodes=new LinkedList<>();
        Map<Node,Integer> inMap=new HashMap<>();
        for(Node node:graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in==0){
                zeroInNodes.add(node);
            }
        }
        List<Node> res=new ArrayList<>();
        while(!zeroInNodes.isEmpty()){
            Node cur=zeroInNodes.poll();
            res.add(cur);
            for(Node next:cur.nexts){
                inMap.put(next,inMap.get(next)-1);
                if(next.in==0){
                    zeroInNodes.add(next);
                }
            }

        }
        return res;
    }
}
