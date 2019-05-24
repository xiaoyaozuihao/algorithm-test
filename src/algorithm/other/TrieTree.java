package other;

/**
 * 前缀树
 * @author xuyh
 * @date 2019/5/23
 */
public class TrieTree {
    public static class TrieNode{
        public int path;
        public int end;
        public TrieNode[] nexts;
        public TrieNode(){
            path=0;
            end=0;
            nexts=new TrieNode[26];//默认都是小写字母
        }
    }

    public static class Trie{
        private TrieNode root;
        public Trie(){
            root=new TrieNode();
        }

        public void insert(String word){
            if(word==null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode cur=root;
            int index;
            for(int i=0;i<chs.length;i++){
                index=chs[i]-'a';
                if(cur.nexts[index]==null){
                    cur.nexts[index]=new TrieNode();
                }
                cur=cur.nexts[index];
                cur.path++;
            }
            cur.end++;
        }

        public void delete(String word){
            if(search(word)!=0){
                char[] chs = word.toCharArray();
                TrieNode cur=root;
                int index;
                for(int i=0;i<chs.length;i++){
                    index=chs[i]-'a';
                    if(--cur.nexts[index].path==0){
                        cur.nexts[index]=null;
                        return;
                    }
                    cur=cur.nexts[index];
                }
                cur.end--;
            }
        }

        public int search(String word){
            if(word==null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode cur=root;
            int index;
            for(int i=0;i<chs.length;i++){
                index=chs[i]-'a';
                if(cur.nexts[index]==null){
                    return 0;
                }
                cur=cur.nexts[index];
            }
            return cur.end;
        }

        public int prefixNumber(String pre){
            if(pre==null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode cur=root;
            int index;
            for(int i=0;i<chs.length;i++){
                index=chs[i]-'a';
                if(cur.nexts[index]==null){
                    return 0;
                }
                cur=cur.nexts[index];
            }
            return cur.path;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa"));
        System.out.println(trie.prefixNumber("zuo"));

    }
}
