package dp;

import java.util.Arrays;

/**
 * 获取最小字典序的字符串
 * @author xuyh
 * @date 2019/5/24
 */
public class LowestLexicography {

    public static String lowestString(String[] strs){
        if(strs==null||strs.length==0){
            return "";
        }
        Arrays.sort(strs, (o1, o2) -> (o1+o2).compareTo(o2+o1));
        String res="";
        for(int i=0;i<strs.length;i++){
            res+=strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));
        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));
    }
}
