package greedy;

/**
 * 背包问题
 * @author xuyh
 * @date 2019/5/28
 */
public class Knapsack {
    public static int maxValue(int[] c,int[] p,int bag){
        return process(c,p,0,0,bag);
    }
    public static int process(int[] weights,int[] values,int i,int alreadWeight,int bag){
        if(alreadWeight>bag){
            return 0;
        }
        if(i==weights.length){
            return 0;
        }
        return Math.max(process(weights,values,i+1,alreadWeight,bag),
                values[i]+process(weights,values,i+1,alreadWeight+weights[i],bag));
    }

    public static int knapsack(int[] c,int[] p,int bag){
        int[][] dp=new int[c.length+1][bag+1];
        for(int i=c.length-1;i>=0;i--){
            for(int j=bag;j>=0;j--){
                dp[i][j]=dp[i+1][j];
                if(j+c[i]<=bag){
                    dp[i][j]=Math.max(dp[i][j],p[i]+dp[i+1][j+c[i]]);
                }
            }
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        int[] c = { 3, 2, 4, 7 };
        int[] p = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(knapsack(c, p, bag));
        System.out.println(maxValue(c, p, bag));
    }

}
