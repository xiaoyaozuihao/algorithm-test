package hash;

import java.util.HashMap;

/**
 * @author xuyh
 * @date 2019/5/21
 */
public class RandomPool {
    public static class Pool<K>{
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int size;
        public Pool(){
            keyIndexMap=new HashMap<>();
            indexKeyMap=new HashMap<>();
            size=0;
        }

        public void insert(K key){
            if(!keyIndexMap.containsKey(key)){
                keyIndexMap.put(key,size);
                indexKeyMap.put(size++,key);
            }
        }
        public void delete(K key){
            if(keyIndexMap.containsKey(key)){
                Integer deleteIndex = keyIndexMap.get(key);
                int lastIndex=--size;
                K lastKey = indexKeyMap.get(lastIndex);
                keyIndexMap.put(lastKey,deleteIndex);
                indexKeyMap.put(deleteIndex,lastKey);
                keyIndexMap.remove(key);
                indexKeyMap.remove(lastIndex);
            }
        }

        public K getRandom(){
            if(size==0){
                return null;
            }
            int randomIndex=(int)(Math.random()*size);
            return indexKeyMap.get(randomIndex);
        }
    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<>();
        pool.insert("zuo");
        pool.insert("cheng");
        pool.insert("yun");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
    }
}
