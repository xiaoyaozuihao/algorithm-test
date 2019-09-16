package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuyh
 * @description: 打印字符串所有子序列
 * @date 2019/9/16
 */
public class PrintAllSubsequences {
    public static void main(String[] args) {
        String abc="abc";
        printAllSubs(abc.toCharArray(),0,"");
        System.out.println("---------------------");
        printAllSubs1(abc.toCharArray(),0);
        System.out.println("---------------------");
        process(abc.toCharArray(),0,new ArrayList<>());
    }

    public static void printAllSubs(char[] chars,int i,String res){
        if(i==chars.length){
            System.out.println(res);
            return;
        }
        printAllSubs(chars,i+1,res+chars[i]);
        printAllSubs(chars,i+1,res);
    }

    public static void printAllSubs1(char[] chars,int i){
        if(i==chars.length){
            System.out.println(chars);
            return;
        }
        printAllSubs1(chars,i+1);
        //记录当前char数组的字符，置为0，表示不要当前字符打印
        char tmp=chars[i];
        chars[i]=0;
        printAllSubs1(chars,i+1);
        //要当前字符打印
        chars[i]=tmp;
    }

    public static void process(char[] chs, int i, List<Character> res) {
        if(i == chs.length) {
            System.out.println(res.stream().map(c->c.toString()).collect(Collectors.joining("")));
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(chs[i]);
        process(chs, i+1, resKeep);
        List<Character> resNoInclude = copyList(res);
        process(chs, i+1, resNoInclude);
    }

    public static List<Character> copyList(List<Character> list){
        List<Character> res=new ArrayList<>();
        for (Character character : list) {
            res.add(character);
        }
        return res;
    }
}
