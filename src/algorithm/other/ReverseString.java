package other;

import java.util.Stack;

/**
 * 字符串反转
 * @author xuyh
 * @date 2019/7/4
 */
public class ReverseString {

    public String reverse1(String str){
        if(str==null||str.length()<2){
            return str;
        }
        String reverse="";
        for(int i=0;i<str.length();i++){
            reverse=str.charAt(i)+reverse;
        }
        return reverse;
    }

    public String reverse2(String str){
        char[] chars = str.toCharArray();
        String res="";
        for(int i=chars.length-1;i>=0;i--){
            res+=chars[i];
        }
        return res;
    }

    public String reverse3(String str){
        char[] chars = str.toCharArray();
        int n=chars.length-1;
        int half=n/2;
        for(int i=0;i<=half;i++){
            char temp=chars[i];
            chars[i]=chars[n-i];
            chars[n-i]=temp;
        }
        return new String(chars);
    }

    public String reverse4(String str){
        return new StringBuilder(str).reverse().toString();
    }

    public String reverse5(String str){
        int length=str.length();
        if(length<=1){
            return str;
        }
        String left=str.substring(0,length/2);
        String right=str.substring(length/2,length);
        return reverse5(right)+reverse5(left);
    }

    public String reverse6(String str){
        int begin=0;
        int end=str.length()-1;
        char[] chars = str.toCharArray();
        while(begin<end){
            chars[begin] = (char) (chars[begin] ^ chars[end]);
            chars[end] = (char) (chars[begin] ^ chars[end]);
            chars[begin] = (char) (chars[begin] ^ chars[end]);
            begin++;
            end--;
        }
        return new String(chars);
    }

    public String reverse7(String str){
        Stack<Character> stack=new Stack<>();
        char[] chars = str.toCharArray();
        for(int i=0;i<chars.length;i++){
            stack.push(chars[i]);
        }
        String res="";
        for(int i=0;i<chars.length;i++){
            res+=stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        String agcdfb = reverseString.reverse7("agcdfb");
        System.out.println(agcdfb);
    }
}
