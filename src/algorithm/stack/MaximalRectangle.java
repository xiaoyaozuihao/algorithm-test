package stack;

import java.util.Stack;

/**
 * 在一个矩形中找到全是1的最大矩形，计算这个矩形中包含的1的数量。
 * 例如：
 * 1 1 0 1
 * 1 0 1 1
 * 1 0 1 1
 * 0 1 1 1   最大矩形包含的数量是6
 *
 * @author xuyh
 * @date 2019/9/20
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        int[][] map={{1,1,1,0},
                     {0,1,1,1},
                     {0,1,1,0},
                     {0,1,1,1},};
        System.out.println(maxRecSize(map));
    }

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        //定义直方图数组，将每一列看作是一个直方图
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                //计算每一列的直方图的大小，遇到有0的直接置为0，否则加1
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxArea, maxRecFromBottom(height));
        }
        return maxArea;
    }

    /**
     * 如果将一个数组表示成直方图，此方法则是找到最大的矩形的面积
     *
     * @param height
     * @return
     */
    private static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        //准备一个单调栈，从栈底到栈顶是从小到大。
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            //栈不为空，且当前值小于等于栈顶，说明要弹出了
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                //定义左边界k,栈弹出后如果为空了，则设为-1，表示没有左边界，否则就是弹出之后的栈顶
                int k = stack.isEmpty() ? -1 : stack.peek();
                //计算当前填出的元素所能形成的矩形面积，右边界是i,左边界是k,所以中间夹着的数是i-k-1
                int curArea = (i - k - 1) * height[j];
                //更新全局最大的矩形面积
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        //栈中剩余的东西需要计算
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            //矩形右边界统一是数组的右边界
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
