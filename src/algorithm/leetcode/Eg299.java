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
    public static void main(String[] args) {
        String secret="1123";
        String guess="0111";
        Eg299 eg299=new Eg299();
        System.out.println(eg299.getHint2(secret, guess));
    }

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

    public String getHint2(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] bucket = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char ss = secret.charAt(i);
            char gg = guess.charAt(i);
            if (ss == gg) {
                bulls++;
            } else {
                bucket[ss - '0']++;
                bucket[gg - '0']--;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (bucket[i] > 0) {
                cows += bucket[i];
            }
        }
        cows = secret.length() - cows - bulls;
        return bulls + "A" + cows + "B";
    }
}
