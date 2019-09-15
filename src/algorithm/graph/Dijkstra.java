package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 狄克斯特拉算法实现
 * 是从一个顶点到其余各顶点的最短路径算法，解决的是有权图中最短路径问题。
 * 迪杰斯特拉算法主要特点是以起始点为中心向外层层扩展，直到扩展到终点为止。
 *
 * @author: xuyh
 * @create: 2019/9/15
 **/
public class Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node node) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> selectedNodes = new HashSet<>();
        distanceMap.put(node, 0);
        Node minNode = node;
        while (minNode != null) {
            int minDistance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, minDistance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), minDistance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnSelectedNode(Map<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    public static HashMap<Node, Integer> dijkstra2(Node node,int size) {
        //建立小根堆，距离给定的结点距离最小的放在堆顶。
        NodeHeap nodeHeap=new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(node,0);
        HashMap<Node,Integer> result=new HashMap<>();
        while(!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.popMinDistance();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : node.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight+distance);
            }
            result.put(cur,distance);
        }
        return result;
    }

    public static class NodeRecord {
        private Node node;
        private int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes;
        private HashMap<Node, Integer> heapIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int heapSize;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            heapSize = 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (isInHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[heapSize] = node;
                heapIndexMap.put(node, heapSize);
                distanceMap.put(node, distance);
                heapInsert(heapSize++);
            }
        }

        public NodeRecord popMinDistance() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, heapSize - 1);
            heapIndexMap.put(nodes[heapSize - 1], -1);
            distanceMap.remove(nodes[heapSize - 1]);
            nodes[heapSize - 1] = null;
            heapify(0, --heapSize);
            return nodeRecord;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean isInHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        private void heapInsert(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int heapSize) {
            int left = 2 * index + 1;
            while (left < heapSize) {
                int smallest = distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        && left + 1 < heapSize ? left + 1 : left;
                smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;
                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = 2 * index + 1;
            }
        }
    }
}
