package leetcode;

/**
 * 实现类似indexOf()的方法
 * @author xuyh
 * @date 2019/9/18
 */
public class Eg28 {

    public static int strStr(String haystack,String needle){
        if(haystack==null||needle==null||haystack.length()<needle.length()){
            return -1;
        }
        if(needle.equals("")){
            return 0;
        }
        int hlen=haystack.length();
        int nlen=needle.length();
        for (int i = 0; i <= hlen - nlen; i++) {
            if(haystack.substring(i,i+nlen).equals(needle)){
                return i;
            }
        }
        return -1;
    }

}
