package binaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 大楼轮廓线问题，lintCode131题
 * 给定二维数组代表大楼信息，行代表每座大楼的信息，列从左到右依次为（起点，终点，高度）
 * 解析：将大楼的信息拆分成两个信息1（起始点，高度，方向向上）2（终止点，高度，方向向下）
 * 再将这两个信息根据起始点坐标进行排序，放入treeMap中，记录最大高度，只要最大高度变化，即产生轮廓
 *
 * @author: xuyh
 * @create: 2019/9/22
 **/
public class BuildingOutline {
    public static void main(String[] args) {
        int[][] buildings = {
                {1, 3, 3},
                {2, 4, 4},
                {5, 6, 1}
        };
        List<List<Integer>> res = getBuildingOutline(buildings);
        System.out.println(res);
    }

    public static class BuildInfo {
        private int h;
        private int index;
        private boolean isUp;

        public BuildInfo(boolean isUp, int index, int h) {
            this.isUp = isUp;
            this.index = index;
            this.h = h;
        }
    }

    public static List<List<Integer>> getBuildingOutline(int[][] buildings) {
        //将大楼信息拆分成两个信息
        BuildInfo[] infos = new BuildInfo[2 * buildings.length];
        for (int i = 0; i < buildings.length; i++) {
            infos[i * 2] = new BuildInfo(true, buildings[i][0], buildings[i][2]);
            infos[i * 2 + 1] = new BuildInfo(false, buildings[i][1], buildings[i][2]);
        }
        //按照先后位置进行排序。
        Arrays.sort(infos, Comparator.comparing(i -> i.index));
        //定义高度的map，记录每个高度出现的次数
        TreeMap<Integer, Integer> hmap = new TreeMap<>();
        //记录每个点的最大高度
        TreeMap<Integer, Integer> pmap = new TreeMap<>();
        for (int i = 0; i < infos.length; i++) {
            int h = infos[i].h;
            int index = infos[i].index;
            //如果是方向向上的，相同高度加1
            if (infos[i].isUp) {
                if (hmap.containsKey(h)) {
                    hmap.put(h, hmap.get(h) + 1);
                } else {
                    hmap.put(h, 1);
                }
            } else {
                //方向向下的如果高度只有一个，直接移除，否则高度减1
                if (hmap.containsKey(h)) {
                    if (hmap.get(h) == 1) {
                        hmap.remove(h);
                    } else {
                        hmap.put(h, hmap.get(h) - 1);
                    }
                }
            }
            //如果hmap为空，记录当前点的最大高度为0，否则取hmap中最大值
            if (hmap.isEmpty()) {
                pmap.put(index, 0);
            } else {
                pmap.put(index, hmap.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;
        for (Map.Entry<Integer, Integer> entry : pmap.entrySet()) {
            Integer index = entry.getKey();
            Integer maxHeight = entry.getValue();
            //说明高度变化
            if (height != maxHeight) {
                if (height != 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(index);
                    list.add(height);
                    res.add(list);
                }
                start = index;
                height = maxHeight;
            }
        }
        return res;
    }
}
