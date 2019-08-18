package stack;

/**
 * 用数组实现栈和队列结构
 *
 * @author xuyh
 * @date 2019/4/22
 */
public class ArrayToStackQueue {
    public static class ArrayStack {
        private Integer[] arr;
        private int index;

        public ArrayStack(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("the initSize is less than 0");
            }
            arr = new Integer[initSize];
            index = 0;
        }

        public Integer peek() {
            if (index == 0) {
                return null;
            }
            return arr[index - 1];
        }

        public void push(int obj) {
            if (index == arr.length) {
                throw new RuntimeException("the Stack is full");
            }
            arr[index++] = obj;
        }

        public int pop() {
            if (index == 0) {
                throw new RuntimeException("the stack is empty");
            }
            return arr[--index];
        }

        public int size() {
            return index;
        }

        public boolean isEmpty() {
            return index == 0;
        }
    }

    public static class ArrayQueue {
        private Integer[] arr;
        private int first;
        private int last;
        private int size;

        public ArrayQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("the initSize is less than 0");
            }
            arr = new Integer[initSize];
            first = 0;
            last = 0;
            size = 0;
        }

        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[first];
        }

        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("the queue is full");
            }
            size++;
            arr[last] = obj;
            last = nextIndex(arr.length,last);
        }

        public int poll() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("the queue is empty");
            }
            size--;
            int tmp = first;
            first = nextIndex(arr.length,first);
            return arr[tmp];
        }

        public int nextIndex(int size, int index) {
            return index == size - 1 ? 0 : index + 1;
        }
    }

    public static void main(String[] args) {
//        ArrayStack arrayStack = new ArrayStack(5);
//        arrayStack.push(1);
//        arrayStack.push(2);
//        arrayStack.push(3);
//        System.out.println(arrayStack.pop());
        ArrayQueue arrayQueue = new ArrayQueue(8);
        arrayQueue.push(1);
        arrayQueue.push(3);
        arrayQueue.push(5);
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.peek());
        arrayQueue.push(4);
        System.out.println(arrayQueue.poll());
        System.out.println(arrayQueue.peek());
    }
}
