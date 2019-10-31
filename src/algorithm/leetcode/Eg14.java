package leetcode;

/**
 * 最长公共前缀
 *
 * @author xuyh
 * @date 2019/10/30
 */
public class Eg14 {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        Eg14 eg14 = new Eg14();
        System.out.println(eg14.longestCommonPrefix0(strs));
        System.out.println(eg14.longestCommonPrefix1(strs));
        System.out.println(eg14.longestCommonPrefix2(strs));
        System.out.println(eg14.longestCommonPrefix3(strs));
    }

    //暴力法1,时间复杂度：O(S)，S 是所有字符串中字符数量的总和。
    public String longestCommonPrefix0(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            //如果后面的某个字符串找不到这个前缀，就将这个前缀长度-1，再继续比较
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    //暴力法2
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            //比较每一个字符串的每一个字符是否相等，如果i到达某个字符串的末尾或者相应字符不相等就返回
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    //分治法,时间复杂度：O(S)，S是所有字符串中字符数量的总和，S=m*n,
    // 最好情况下，算法会进行minLen*n 次比较，其中 minLen是数组中最短字符串的长度。
    //空间复杂度 O(m*log(n))
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = l + (r - l) / 2;
            String lPrefix = longestCommonPrefix(strs, l, mid);
            String rPrefix = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lPrefix, rPrefix);
        }
    }

    private String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

    //时间复杂度：O(S⋅log(n))，其中 S 所有字符串中字符数量的总和
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int lo = 1;
        int hi = minLen;
        while (lo <= hi) {
            //可以获得下中位数
            int mid = lo + (hi - lo) / 2;
            if (isCommonPrefix(strs, mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return strs[0].substring(0, (lo + hi) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    //前缀树解法
    //建立字典树的时间复杂度为 O(S)O(S)。在字典树中查找字符串 qq 的最长公共前缀在最坏情况下需要 O(m)O(m) 的时间
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Trie trie = new Trie();
        for (int i = 0; i < strs.length; i++) {
            trie.insert(strs[i]);
        }
        return trie.searchLongestPrefix(strs[0]);
    }

    class TrieNode {
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;
        private int size;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
            size++;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public int getLinks() {
            return size;
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char curChar = word.charAt(i);
                if (!node.containsKey(curChar)) {
                    node.put(curChar, new TrieNode());
                }
                node = node.get(curChar);
            }
            node.setEnd();
        }

        private String searchLongestPrefix(String word) {
            TrieNode node = root;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                //路径上包含该字符，路径上的每一个节点都有且仅有一个孩子，且未结束，就可以继续向后查找
                if (node.containsKey(letter) && node.getLinks() == 1 && !node.isEnd) {
                    sb.append(letter);
                    node = node.get(letter);
                } else {
                    return sb.toString();
                }
            }
            return sb.toString();
        }
    }
}
