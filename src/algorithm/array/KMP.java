package array;

/**
 * kmp算法，用来查找一个字符串是否在另一个字符串中。
 * @author xuyh
 * @date 2019/9/17
 */
public class KMP {
    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() == 0 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] nextArray = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            //要比较的两个字符相等，两个一起走
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
                //mi在nextArray中的值如果为-1，说明已经来到0位置也配不上ss[si],说明开头都配不上
                //那就换个开头，所以si向后走
            } else if (nextArray[mi] == -1) {
                si++;
            } else {//如果不为-1，那么mi就可以向前跳，跳到上一个具有最大前缀的字符索引的位置，从跳到的那个位置再开始配
                mi = nextArray[mi];
            }
        }
        //mi已经到达字符串末尾，说明配出来了，返回初始字符索引，否则-1。
        return mi == ms.length ? si - mi : -1;
    }

    /**
     * 生成字符串的next数组。
     * next[i]的定义是：当前字符之前的最大前缀和后缀的字符串长度的值,不能包含全部字符。
     * 例如aaaab,next[4]=3,即最大前缀为aaa,后缀也是，前缀不能包含最后一个字符a，后缀不能包含第一个字符a
     * 显然0位置不存在，所以规定为-1，1位置前面只有一个字符，所以规定为0.
     * @param ms
     * @return
     */
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        //表示当前位置
        int pos = 2;
        //既表示跳到的最大前缀的后一个位置，也表示当前位置最大前缀和后缀的长度。
        //初始值就是开始位置
        int cn = 0;
        //0位置和1位置默认为-1和0，从2位置开始
        while (pos < next.length) {
            //第一次循环如果第1个元素和第二个元素相等，说明next数组第三个位置的值为1
            //表示这个位置之前最大的前缀和后缀长度为1，cn++表示跳到最大前缀的后一个位置，进行下次循环。
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {//cn>0,说明还能往前跳
                cn = next[cn];
            } else {//不能再向前跳，说明当前位置之前的最大前缀和后缀长度为0。
                next[pos++] = 0;
            }
        }
        return next;
    }
}
