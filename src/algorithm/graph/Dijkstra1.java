package graph;

import java.util.Scanner;

/**
 * 迪克斯特拉算法，邻接矩阵法
 *
 * @author: xuyh
 * @create: 2019/9/15
 **/
public class Dijkstra1 {
    public static void main(String[] args) {
        int n, m;//代表n个点，m条边。
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] value = new int[n + 1][n + 1];
        int[] dis=new int[n+1];
        int x=0;
        //初始化各个点之间的距离。
        for (int i = 0; i < n; i++) {
            dis[i]=Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                value[i][j] = (i == j) ? 0 : -1;
            }
        }
        //初始化各个边的权重
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int v = sc.nextInt();
            value[a][b] = v;
            if(a==x){
                dis[b]=v;
            }
        }
        search(x,dis,value,n);
    }

    public static void search(int x,int[] dis,int[][] value,int n){
        boolean[] mark=new boolean[n+1];
        for (int i = 0; i < n; i++) {
            mark[i]=false;
        }
        mark[x]=true;//表示当前这个点已经加过。
        dis[x]=0;//自己走自己,距离为0
        int count=1;//表示当前加了几个点
        while(count<=n){
            int loc=0;//找到距离原结点最小的那个点的索引。
            int min=Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if(!mark[i] && dis[i]<min){
                    min=dis[i];
                    loc=i;
                }
            }
            //所有点都加过
            if(loc==0){
                break;
            }
            mark[loc]=true;
            count++;
            for (int i = 0; i < n; i++) {
                if(!mark[i]&&value[loc][i]!=-1
                &&(dis[loc]+value[loc][i]<dis[i])){
                    dis[i]=dis[loc]+value[loc][i];
                }
            }
        }
        System.out.println("以"+x +"为起点的最短路径");
        for (int i = 0; i < n; i++) {
            if(dis[i]!= Integer.MAX_VALUE){
                System.out.println(i+"最短为："+dis[i]);
            }else{
                System.out.println(i+"没有路");
            }
        }

    }
}
