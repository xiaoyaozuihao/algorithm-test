package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * n个会议要在一天内召开，时间各不相同，如何安排能尽可能多的安排更多的会议
 * @author xuyh
 * @date 2019/5/24
 */
public class BestArrange {
    public static class Meeting{
        public int start;
        public int end;
        public Meeting(int start,int end){
            this.start=start;
            this.end=end;
        }
    }
    public static int bestArrange(Meeting[] meetings,int start){
        Arrays.sort(meetings, Comparator.comparingInt(m -> m.end));
        int res=0;
        for(int i=0;i<meetings.length;i++){
            if(start<=meetings[i].start){
                res++;
                start=meetings[i].end;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
