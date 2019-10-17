package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猜数字游戏
 *
 * @author xuyh
 * @date 2019/10/17
 */
public class Eg299 {
    public String getHint(String secret, String guess) {
        int cows = 0;
        int bulls = 0;
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            char ss = secret.charAt(i);
            char gg = guess.charAt(i);
            if (ss == gg) {
                bulls++;
            } else {
                list.add(gg);
                if (map.containsKey(ss)) {
                    map.put(ss, map.get(ss) + 1);
                } else {
                    map.put(ss, 1);
                }
            }
        }
        for (Character cc : list) {
            if (map.containsKey(cc)) {
                cows++;
                map.put(cc, map.get(cc) - 1);
                if (map.get(cc) == 0) {
                    map.remove(cc);
                }
            }
        }
        return bulls + "A" + cows + "B";
    }

    public String getHint1(String secret, String guess) {
        int bull = 0;     // 公牛数
        int cow = 0;     // 母牛数
        int[] mapS = new int[10];
        int[] mapG = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char ss = secret.charAt(i);
            char cc = guess.charAt(i);
            if (ss == cc)
                bull++;
            else {
                mapS[ss - '0']++;
                mapG[cc - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cow += Math.min(mapG[i], mapS[i]);
        }
        return bull + "A" + cow + "B";
    }
}
