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
                throw new ArrayIndexOutOfBoundsException("the Stack is full");
            }
            arr[index++] = obj;
        }

        public int pop() {
            if (index == 0) {
                throw new ArrayIndexOutOfBoundsException("the stack is empty");
            }
            return arr[--index];
        }
    }

    public static class ArrayQueue {
        private Integer[] arr;
        private int start;
        private int end;
        private int size;

        public ArrayQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("the initSize is less than 0");
            }
            arr = new Integer[initSize];
            start = 0;
            end = 0;
            size = 0;
        }

        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[start];
        }

        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("the queue is full");
            }
            size++;
            arr[end] = obj;
            end = end == arr.length - 1 ? 0 : end + 1;
        }

        public int poll() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("the queue is empty");
            }
            size--;
            int tmp =start;
            start = start == arr.length - 1 ? 0 : start + 1;
            return arr[tmp];
        }
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        System.out.println(arrayStack.pop());
        ArrayQueue arrayQueue=new ArrayQueue(8);
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
