package other;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 跳表的实现
 *
 * @author: xuyh
 * @create: 2019/10/3
 **/
public class SkipListSample {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.add(2);
        skipList.add(3);
        skipList.add(5);
        skipList.add(4);
        skipList.add(7);
        skipList.add(1);
        skipList.add(9);
        System.out.println(skipList.size());
        System.out.println(skipList.getHead());
        System.out.println(skipList.find(3));
        System.out.println(skipList.contains(1));
        System.out.println(skipList.contains(19));
        skipList.delete(1);
    }

    public static class SkipListNode {
        private Integer value;
        private ArrayList<SkipListNode> nextNodes;//当前结点有几层，list就有几个元素

        public SkipListNode(Integer value) {
            this.value = value;
            nextNodes = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "SkipListNode{" +
                    "value=" + value +
                    ", nextNodes=" + nextNodes.stream().filter(n -> n != null).map(n -> n.value).collect(Collectors.toList()) +
                    '}';
        }
    }

    public static class SkipList {
        private SkipListNode head;//头结点，辅助结构
        private int maxLevel;//最大层数
        private int size;
        private static final double PROBABILITY = 0.5;//概率大小

        public SkipList() {
            maxLevel = 0;
            size = 0;
            head = new SkipListNode(null);//头结点初始化值都为空，每一层都是空
            head.nextNodes.add(null);
        }

        public SkipListNode getHead() {
            return head;
        }

        public void add(Integer value) {
            if (!contains(value)) {
                size++;
                int level = 0;
                //扔骰子，扔出大的就停
                while (Math.random() < PROBABILITY) {
                    level++;
                }
                //如果扔出的层数大于最大层数，则头结点扩充层数。
                while (level > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                //建出新节点，插入到相应的位置
                SkipListNode newNode = new SkipListNode(value);
                SkipListNode cur = this.head;
                int levelAll = maxLevel;
                do {
                    //每次都从最大层开始找距离目标数最近且小于目标的数
                    //如果目标层数小于最大层数，最大层数就不断向下走，直到两个层数相等时开始一起走，建立连接
                    cur = findNext(value, cur, levelAll);
                    if (level == levelAll) {
                        //采用前插法，每次都往0位置插入，随着level不断递减，最初插入的值就跑到了最高层
                        //设置新结点当前层应该连接的下一个的结点
                        newNode.nextNodes.add(0, cur.nextNodes.get(level));
                        //设置距离目标结点最近且小于目标结点的结点在当前层的关联关系
                        cur.nextNodes.set(level, newNode);
                        level--;
                    }
                } while (levelAll-- > 0);
            }
        }

        public void delete(Integer value) {
            //从高层向下找，一直找到0层为止
            SkipListNode deleteNode = find(value);
            if (deleteNode != null && Objects.equals(deleteNode.value, value)) {
                size--;
                int level = maxLevel;
                SkipListNode cur = head;
                do {
                    cur = findNext(deleteNode.value, cur, level);
                    //从最大层数开始查找，当待删除结点的层数大于等于最大层数时(level从0开始)，以后的每一层都要重新设置关联
                    if (deleteNode.nextNodes.size() > level) {
                        //将待删除结点的前一个结点nextNodes设置为待删除结点当前层的后一个结点
                        cur.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }

        public int size() {
            return size;
        }

        public boolean contains(Integer value) {
            SkipListNode node = find(value);
            return node != null && Objects.equals(node.value, value);
        }

        public SkipListNode find(Integer value) {
            return find(value, head, maxLevel);
        }

        private SkipListNode find(Integer value, SkipListNode cur, int level) {
            SkipListNode res;
            do {
                //从给定层开始找，一直找到0层为止
                res = findNext(value, cur, level);
            } while (level-- > 0);
            return res;
        }

        private SkipListNode findNext(Integer value, SkipListNode cur, int level) {
            SkipListNode next = cur.nextNodes.get(level);
            while (next != null) {
                //从给定层开始找，一直找到距离给定值最近且小于给定值的结点停止
                if (lessThan(value, next.value)) {
                    break;
                }
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        private boolean lessThan(Integer v1, Integer v2) {
            return !(v1 == v2) || v1 != null && v1.compareTo(v2) < 0;
        }
    }
}
