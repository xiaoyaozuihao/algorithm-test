package queue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个最近最少频次使用的缓存结构
 *
 * @author xuyh
 * @date 2019/9/29
 */
public class LFUCacheSample {
    public static void main(String[] args) {
        LFUCacheSample sample = new LFUCacheSample();
        LFU<Integer, Integer> cache = sample.new LFU<>(3);
        cache.put(2, 2);
        cache.put(1, 1);
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println("----------------------------");
        cache.put(3, 3);
        cache.put(4, 4);
        //1、2元素都有访问次数，放入3后缓存满，加入4时淘汰3
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(4));
        System.out.println("-----------------------------");
        cache.put(5, 5);
        //目前2访问2次，1访问一次，4访问一次，由于4的时间比较新，放入5的时候移除1元素。
        System.out.println(cache.get(1));
        cache.cache.entrySet().forEach(entry -> {
            System.out.print(entry.getValue() + " ");
        });
        System.out.println();
        System.out.println("------------------------------");
        LFUCache<Integer, Integer> lfu = sample.new LFUCache<>(3);
        lfu.put(1, 1);
        lfu.put(2, 2);
        lfu.put(3, 3);
        lfu.put(4, 4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(4));
        System.out.println("-----------------------------");
        lfu.put(5, 5);
        lfu.records.entrySet().forEach(r -> System.out.print(r.getValue().value + " "));
    }

    public class Node<K, V> {
        private K key;
        private V value;
        private int times;
        private Node<K, V> up;
        private Node<K, V> down;

        public Node(K key, V value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public class NodeList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;
        private NodeList<K, V> pre;
        private NodeList<K, V> next;

        public NodeList(Node<K, V> node) {
            head = node;
            tail = node;
        }

        //从头部添加元素，默认头部为最频繁使用的
        public void addNodeFromHead(Node<K, V> node) {
            node.down = head;
            head.up = node;
            head = node;
        }

        public boolean isEmpty() {
            return head == null;
        }

        //将结点从链表中移除
        public void deleteNode(Node<K, V> node) {
            //只有一个元素
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                //换头
                if (head == node) {
                    head = node.down;
                    head.up = null;
                    //换尾
                } else if (tail == node) {
                    tail = node.up;
                    tail.down = null;
                } else {
                    //结点在中间，断开连接
                    node.up.down = node.down;
                    node.down.up = node.up;
                }
            }
            //node与链表彻底断开连接
            node.up = null;
            node.down = null;
        }
    }

    //实现原理，每一种使用频率建立一个nodeList,将结点挂在对应的频率链表中，如果某个链表中没有元素，就彻底移除。
    public class LFUCache<K, V> {
        private int capacity;
        private int size;
        private HashMap<K, Node<K, V>> records;//某个key对应的结点
        private HashMap<Node<K, V>, NodeList<K, V>> heads;//某个结点对应的频率链表
        private NodeList<K, V> headList;//当前缓存中使用频率最小的头链表

        public LFUCache(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException();
            }
            this.capacity = capacity;
            size = 0;
            records = new HashMap<>();
            heads = new HashMap<>();
            headList = null;
        }

        public void put(K key, V value) {
            //如果记录中有，使用次数+1，移动结点到次数+1的链表中
            if (records.containsKey(key)) {
                Node<K, V> node = records.get(key);
                node.value = value;
                node.times++;
                NodeList<K, V> oldNodeList = heads.get(node);
                move(node, oldNodeList);
            } else {
                //容量达到最大，移除使用频率最小的，即头链表的尾结点
                if (size == capacity) {
                    Node<K, V> tail = headList.tail;
                    headList.deleteNode(tail);
                    modifyHeadList(headList);
                    records.remove(tail.key);
                    heads.remove(tail);
                    size--;
                }
                //添加新结点，如果没有头链表，创建头链表放入新结点
                //如果头链表的使用次数等于1，直接放入头链表
                //否则建立新的链表，更换头链表
                Node node = new Node(key, value, 1);
                if (headList == null) {
                    headList = new NodeList<>(node);
                } else {
                    if (headList.head.times == 1) {
                        headList.addNodeFromHead(node);
                    } else {
                        NodeList newList = new NodeList<>(node);
                        newList.next = headList;
                        headList.pre = newList;
                        headList = newList;
                    }
                }
                //相应结构进行更新
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        //将某个结点从旧的链表中移除添加到使用频率+1的链表中
        private void move(Node<K, V> node, NodeList<K, V> oldNodeList) {
            oldNodeList.deleteNode(node);
            //获取比当前结点的目标链表频率小的链表，如果旧链表成功移除，则为旧链表的前一个，否则还是旧链表
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.pre : oldNodeList;
            //获取比当前结点的目标链表频率大的链表
            NodeList nextList = oldNodeList.next;
            //如果不存在，就新建，建立关联关系，主要是一些边界的处理
            if (nextList == null) {
                NodeList newList = new NodeList(node);
                if (preList != null) {
                    preList.next = newList;
                }
                newList.pre = preList;
                if (headList == null) {
                    headList = newList;
                }
                //别忘了更新结点对应的链表
                heads.put(node, newList);
            } else {
                //存在判断使用频率，相等就直接添加
                if (nextList.head.times == node.times) {
                    nextList.addNodeFromHead(node);
                    heads.put(node, nextList);
                } else {
                    //不相等，新建链表，重建关联关系
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.pre = preList;
                    newList.next = nextList;
                    nextList.pre = newList;
                    if (headList == nextList) {
                        headList = newList;
                    }
                    heads.put(node, newList);
                }
            }
        }

        //如果某个结点从链表中被移除后，链表为空，则删除这个链表，返回true,否则返回false
        private boolean modifyHeadList(NodeList<K, V> nodeList) {
            if (nodeList.isEmpty()) {
                //头结点等于要移除的结点，则换头
                if (headList == nodeList) {
                    headList = nodeList.next;
                    //如果换头后为空，则代表缓存中没有数据了
                    //否则断开与旧链表的关联
                    if (headList != null) {
                        headList.pre = null;
                    }
                } else {
                    //中间位置的移除操作
                    nodeList.pre.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.pre = nodeList.pre;
                    }
                }
                return true;
            }
            return false;
        }

        public V get(K key) {
            if (!records.containsKey(key)) {
                return null;
            }
            Node<K, V> node = records.get(key);
            node.times++;
            NodeList nodeList = heads.get(node);
            move(node, nodeList);
            return node.value;
        }
    }

    public class LFU<k, v> {
        private final int capcity;
        private Map<k, v> cache = new HashMap<>();
        private Map<k, HitRate> count = new HashMap<>();

        public LFU(int capcity) {
            this.capcity = capcity;
        }

        public void put(k key, v value) {
            v v = cache.get(key);
            if (v == null) {
                if (cache.size() == capcity) {
                    removeElement();
                }
                count.put(key, new HitRate(key, 1, System.nanoTime()));
            } else {
                addHitCount(key);
            }
            cache.put(key, value);
        }

        public v get(k key) {
            v value = cache.get(key);
            if (value != null) {
                addHitCount(key);
                return value;
            }
            return null;
        }

        //移除元素
        private void removeElement() {
            HitRate hr = Collections.min(count.values());
            cache.remove(hr.key);
            count.remove(hr.key);
        }

        //更新访问元素状态
        private void addHitCount(k key) {
            HitRate hitRate = count.get(key);
            hitRate.hitCount = hitRate.hitCount + 1;
            hitRate.lastTime = System.nanoTime();
        }

        //内部类
        class HitRate implements Comparable<HitRate> {
            private k key;
            private int hitCount;
            private long lastTime;

            private HitRate(k key, int hitCount, long lastTime) {
                this.key = key;
                this.hitCount = hitCount;
                this.lastTime = lastTime;
            }

            @Override
            public int compareTo(HitRate o) {
                int compare = Integer.compare(this.hitCount, o.hitCount);
                return compare == 0 ? Long.compare(this.lastTime, o.lastTime) : compare;
            }
        }
    }
}
