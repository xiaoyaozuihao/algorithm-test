package array;

/**
 * 给你一个非空字符串，判断它能否通过重复它的某一个子串若干次（两次及以上）得到。
 * 字符串由小写字母组成，并且它的长度不会超过10000。
 * 例如：输入abab ,返回true；输入aba,返回false
 * @author: xuyh
 * @create: 2019/9/17
 **/
public class RepeatedSubstrPattern {

    public static boolean repeatedSubstrPattern(String str){
        if(str==null){
            return false;
        }
        if(str.length()<4){
            if(str.length()==2){
                return str.charAt(0)==str.charAt(1);
            }
            return false;
        }
        return true;
    }
}
