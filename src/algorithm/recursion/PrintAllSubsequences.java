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
        printAllSubs2(abc.toCharArray(),0,new ArrayList<>());
        System.out.println("---------------------");
        printAllSubs3(abc.toCharArray(),0,new StringBuilder());
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

    public static void printAllSubs3(char[] chars,int i,StringBuilder sb){
        if(i==chars.length){
            System.out.println(sb.toString());
            return;
        }
        sb.append(chars[i]);
        printAllSubs3(chars,i+1,sb);
        sb.deleteCharAt(sb.length()-1);
        printAllSubs3(chars,i+1,sb);
    }

    public static void printAllSubs2(char[] chs, int i, List<Character> res) {
        if(i == chs.length) {
            System.out.println(res.stream().map(c->c.toString()).collect(Collectors.joining("")));
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(chs[i]);
        printAllSubs2(chs, i+1, resKeep);
        List<Character> resNoInclude = copyList(res);
        printAllSubs2(chs, i+1, resNoInclude);
    }

    public static List<Character> copyList(List<Character> list){
        List<Character> res=new ArrayList<>();
        for (Character character : list) {
            res.add(character);
        }
        return res;
    }
}
