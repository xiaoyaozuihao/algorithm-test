package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 最小基因变化
 *
 * @author xuyh
 * @date 2019/5/15
 */
public class MinMutation {
    public static int minMutation(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0) {
            return -1;
        }
        // 使用队列进行广度优先搜索，分别记录探索的字符串与此时探索的次数
        Queue<String> queueStr = new LinkedList<>();
        Queue<Integer> queueTimes = new LinkedList<>();
        queueStr.offer(start);
        queueTimes.offer(0);
        while (queueStr.peek() != null) {
            String lastStr = queueStr.poll();
            int lastTime = queueTimes.poll();
            for (int k = 0; k < bank.length; k++) {
                String str = bank[k];
                if (str == null) {
                    continue;
                }
                int gapNum = 0;
                for (int i = 0; i < 8; i++) {
                    if (lastStr.charAt(i) != str.charAt(i)) {
                        if (gapNum++ == 1) {
                            break;
                        }
                    }
                }
                if (gapNum > 1) {
                    continue;
                } else if (str.equals(end)) {
                    return lastTime + 1;
                } else {
                    bank[k] = null;
                    queueStr.offer(str);
                    queueTimes.offer(lastTime + 1);
                }
            }
        }
        return -1;
    }

    public static int minMutation1(String start, String end, String[] bank) {
        if (!isTrue(start,end)&& bank.length>0){
            return 1;
        }
        Stack<String> s1 = new Stack<>();
        Stack<Integer> step =new Stack();
        s1.push(start);
        step.push(0);
        while (!s1.empty()){
            String ss =s1.pop();
            int sum = step.pop();
            for (int i=0;i<bank.length;i++){
                if(bank[i]==null){
                    continue;
                }
                if (isTrue(ss,bank[i])){
                    continue;
                }else{
                    if (bank[i]==end){
                        return sum+1;
                    }
                    step.push(sum+1);
                    s1.push(bank[i]);
                    bank[i]=null;
                }
            }
        }
        return -1;
    }
    public static boolean isTrue(String start,String end){
        int num=0;
        for (int i=0;i<8;i++){
            if (start.charAt(i)!=end.charAt(i)){
                if(num++==1){
                    break;
                }
            }
        }
        return num>1;
    }

    public static void main(String[] args) {
//        System.out.println(minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
//        System.out.println(minMutation1("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
        System.out.println(minMutation1("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
    }
}
