package binaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 大楼轮廓线问题,解法优化
 *
 * @author: xuyh
 * @create: 2019/9/22
 **/
public class BuildingOutline1 {
    private static final int UP = 0;
    private static final int DOWN = 1;

    private class Pair implements Comparable {
        int index, height, status;

        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }

        public int compareTo(Object o) {
            Pair p = (Pair) o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }
            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }
        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }


    //使用堆来做
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings.length == 0) {
            return res;
        }
        Queue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.index == b.index) {
                if (a.status == b.status) {
                    //for ending points, we delete lower first
                    if (a.status == 0) {
                        return a.h - b.h;
                    }
                    //for start points, we add highest first
                    return b.h - a.h;
                }
                return b.status - a.status;
            }
            return a.index - b.index;
        });

        //add start and end Node for each building
        for (int[] building : buildings) {
            pq.offer(new Node(building[0], building[2], 1));
            pq.offer(new Node(building[1], building[2], 0));
        }
        TreeMap<Integer, Integer> heights = new TreeMap<>();
        int currH = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            //for start point, we add height into treemap
            //else we delete height from treemap
            if (curr.status == 1) {
                heights.putIfAbsent(curr.h, 0);
                heights.put(curr.h, heights.get(curr.h) + 1);
            } else {
                heights.put(curr.h, heights.get(curr.h) - 1);
                if (heights.get(curr.h) == 0) {
                    heights.remove(curr.h);
                }
            }
            //对于相同index  我们先加最高start point, 且最后删最高end point
            //所以我们可以保证进入循环的 高度是这个点往后的最大高度, 更新我们的currH
            if (heights.isEmpty() || heights.lastKey() != currH) {
                if (heights.isEmpty()) {
                    currH = 0;
                } else {
                    currH = heights.lastKey();
                }
                res.add(new int[]{curr.index, currH});
            }

        }
        return res;
    }

    private class Node {
        int h;
        //1 for end, 0 for start
        int status;
        int index;
        public Node(int index, int h, int status) {
            this.index = index;
            this.status = status;
            this.h = h;
        }
    }
}
