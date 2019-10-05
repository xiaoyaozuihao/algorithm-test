package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Integer.lowestOneBit(0x80000000));
        System.out.println((Integer.toBinaryString(-3)));
        System.out.println((-3 >> 63));
        System.out.println((-3 >> 31) & 1);
    }

    static class Node {
        private Node[] nexts = new Node[2];
    }

    static class NumTrie {
        private Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                cur.nexts[path] = cur.nexts[path] != null ? cur.nexts[path] : new Node();
                cur = cur.nexts[path];
            }
        }

        public int maxEor(int num) {
            Node cur = head;
            int eor = 0;
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = i == 31 ? path : (path ^ 1);
                best = cur.nexts[best] == null ? (best ^ 1) : best;
                res |= (best ^ path) << i;
                cur = cur.nexts[best];
            }
            return res;
        }
    }
}