package queue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 设计一个最近最少使用的缓存结构，即LRU
 *
 * @author xuyh
 * @date 2019/9/29
 */
public class LRUCacheSample {
    public static void main(String[] args) {
        LRUCacheSample sample=new LRUCacheSample();
        LRUCache testCache=sample.new LRUCache(3);
        testCache.put("A", 1);
        testCache.put("B", 2);
        testCache.put("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.put("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));
        System.out.println("-------------------------------");
        LRUCache1 testCache1=sample.new LRUCache1(3);
        testCache1.put("A", 1);
        testCache1.put("B", 2);
        testCache1.put("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache1.put("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));
        System.out.println("-------------------------------");
        LRUCache2 testCache2 = sample.new LRUCache2(3);
        testCache2.put("A", 1);
        testCache2.put("B", 2);
        testCache2.put("C", 3);
        System.out.println(testCache2.get("B"));
        System.out.println(testCache2.get("A"));
        testCache2.put("D", 4);
        System.out.println(testCache2.get("D"));
        System.out.println(testCache2.get("C"));
    }

    //使用自定义类型节点和链表以及hashMap手动实现
    public class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> pre;
        private Node<K, V> next;

        public Node(K key,V value) {
            this.key=key;
            this.value = value;
        }
    }

    //自定义链表结构
    public class NodeLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node<K, V> node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        //链表尾部是最近最常使用的
        public void moveToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }
            //要移动的结点是否是头结点，进行相关处理，就是将这个结点独立出来
            if (head == node) {
                head = node.next;
                head.pre = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            //添加到尾部
            node.pre = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        //移除头部就是移除最近最少使用的结点
        public Node<K, V> removeHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = res.next;
                res.next = null;
                head.pre = null;
            }
            return res;
        }
    }

    public class LRUCache<K, V> {
        private HashMap<K, Node<K, V>> map;
        private NodeLinkedList<K, V> nodeList;
        private int capacity;

        public LRUCache(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException();
            }
            this.capacity = capacity;
            map = new HashMap<>();
            nodeList = new NodeLinkedList<>();
        }

        public V get(K key) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                nodeList.moveToTail(node);
                return node.value;
            }
            return null;
        }

        public void put(K key, V value) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                node.value = value;
                nodeList.moveToTail(node);
            } else {
                Node<K, V> node = new Node<>(key, value);
                map.put(key, node);
                nodeList.addNode(node);
                if (map.size() == capacity + 1) {
                    removeMostUnusedCache();
                }
            }
        }

        private void removeMostUnusedCache() {
            Node<K, V> removeNode = nodeList.removeHead();
            map.remove(removeNode.key);
        }
    }

    //使用委派的方式，不需要继承
    public class LRUCache1<K, V> {
        private int MAX_CACHE_SIZE;
        private final float DEFAULT_LOAD_FACTOR = 0.75f;
        LinkedHashMap<K, V> map;

        public LRUCache1(int cacheSize) {
            MAX_CACHE_SIZE = cacheSize;
            //根据cacheSize和加载因子计算hashmap的capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容
            int capaticy = (int) Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1;
            map = new LinkedHashMap<K, V>(capaticy, DEFAULT_LOAD_FACTOR, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > MAX_CACHE_SIZE;
                }
            };
        }

        public synchronized void put(K k, V v) {
            map.put(k, v);
        }

        public synchronized V get(K key) {
            return map.get(key);
        }

        public synchronized void remove(K key) {
            map.remove(key);
        }

        public synchronized int size() {
            return map.size();
        }

        public synchronized void clear() {
            map.clear();
        }
    }

    //使用java自带的数据结构,继承LinkedHashMap
    public class LRUCache2<K, V> extends LinkedHashMap<K, V> {
        private int cacheSize;

        public LRUCache2(int cacheSize) {
            //初始化linkedHashMap,当参数accessOrder为true时，即会按照访问顺序排序，最近访问的放在最前，最早访问的放在后面
            super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
            this.cacheSize = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > cacheSize;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<K, V> entry : entrySet()) {
                sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
            }
            return sb.toString();
        }
    }
}
