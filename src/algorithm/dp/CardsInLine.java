package dp;

/**
 * 排成一条线的纸牌博弈问题
 * 给定一个整形数组，代表分值不同的排成一条线的纸牌，玩家A和B依次拿走，规定A先拿，A和B都能看到所有的牌，且都绝顶聪明
 * 每个玩家只能拿走最左或最右的，返回最终获胜者的分数
 *
 * @author: xuyh
 * @create: 2019/10/5
 **/
public class CardsInLine {
    public static void main(String[] args) {
        int[] arr = {1, 9, 1};
        System.out.println(win1(arr));
        System.out.println(winDp(arr));
    }

    public static int winDp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] firstHand = new int[arr.length][arr.length];
        int[][] backHand = new int[arr.length][arr.length];
        //j始终大于i，所以j代表从右取，i代表从左取
        for (int j = 0; j < arr.length; j++) {
            //分析baseCase可得firstHand对角线的值为数组的值，backHand为0.
            firstHand[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                firstHand[i][j] = Math.max(arr[i] + backHand[i + 1][j], arr[j] + backHand[i][j - 1]);
                backHand[i][j] = Math.min(firstHand[i + 1][j], firstHand[i][j - 1]);
            }
        }
        return Math.max(firstHand[0][arr.length - 1], backHand[0][arr.length - 1]);
    }

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(firstHand(arr, 0, arr.length - 1), backHand(arr, 0, arr.length - 1));
    }

    private static int firstHand(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + backHand(arr, i + 1, j), arr[j] + backHand(arr, i, j - 1));
    }

    private static int backHand(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(firstHand(arr, i + 1, j), firstHand(arr, i, j - 1));
    }
}
