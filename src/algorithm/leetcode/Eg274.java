package leetcode;

import java.util.Arrays;

/**
 * H指数
 *
 * @author xuyh
 * @date 2019/10/22
 */
public class Eg274 {
    public static void main(String[] args) {
        Eg274 eg274=new Eg274();
        int[] citations={3,0,1,5,7};
        System.out.println(eg274.hIndex(citations));
        System.out.println(eg274.hIndex1(citations));
    }
    //排序后，将其转换为直方图，本质是求最大的正方形的边长
    //基于比较的排序算法都有下限时间复杂度O(N*logN)
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i++;
        }
        return i;
    }

    //使用计数排序可以将时间复杂度降低到O(N)
    //但是考虑一个问题，引文的引用次数可能超过文章数，使用计数排序会造成大量空间浪费
    //结合题意思考分析后发现，h指数不可能超过文章数，所以将超过文章数n的引用次数降为n，不会改变h指数，这样就可以让计数排序变得可行
    public int hIndex1(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        for (int c : citations) {
            papers[Math.min(n, c)]++;
        }
        int k = n;
        //这个循环的编程技巧比较高，可以参考hIndex2方法，更加直白
        for (int s = papers[n]; k > s; s += papers[k]) {
            k--;
        }
        return k;
    }


    public int hIndex2(int[] citations) {
        int n = citations.length;
        if(n == 0){
            return 0;
        }
        int[] buckets = new int[n+1];
        for(int c : citations){
            if(c>=n){
                buckets[n]++;
            }else{
                buckets[c]++;
            }
        }
        int count = 0;
        for(int i=n;i>=0;i--){
            count += buckets[i];
            //引文次数依次累加，当大于等于文章数时，即找到最大。
            if(count>=i){
                return i;
            }
        }
        return 0;
    }
}
