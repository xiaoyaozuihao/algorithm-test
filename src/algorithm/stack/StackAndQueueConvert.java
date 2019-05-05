package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 栈结构与队列结构转换
 * @author xuyh
 * @date 2019/4/22
 */
public class StackAndQueueConvert {
    public static class TwoQueuesStack {
        private Queue<Integer> dataQueue;
        private Queue<Integer> helpQueue;
        public TwoQueuesStack(){
            dataQueue=new LinkedList<>();
            helpQueue=new LinkedList<>();
        }
        public void push(int obj){
            dataQueue.add(obj);
        }

        public int peek(){
            if(dataQueue.isEmpty()){
                throw new RuntimeException("the stack is empty");
            }
            while(dataQueue.size()!=1){
                helpQueue.add(dataQueue.poll());
            }
            int res= dataQueue.poll();
            helpQueue.add(res);
            swap();
            return res;
        }
        public int pop(){
            if(dataQueue.isEmpty()){
                throw new RuntimeException("the stack is empty");
            }
            while(dataQueue.size()!=1){
                helpQueue.add(dataQueue.poll());
            }
            int res=dataQueue.poll();
            swap();
            return res;
        }
        public void swap(){
            Queue<Integer> tmp=helpQueue;
            helpQueue=dataQueue;
            dataQueue=tmp;
        }

    }

    public static class TwoStacksQueue{
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStacksQueue(){
            pushStack=new Stack<>();
            popStack=new Stack<>();
        }
        public void push(int obj){
            pushStack.push(obj);
        }
        public int pop(){
            if(popStack.empty()&&pushStack.empty()){
                throw new RuntimeException("this queue is empty");
            }else if(popStack.isEmpty()){
                while(!pushStack.empty()){
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.pop();
        }

        public int peek(){
            if(popStack.isEmpty()&&pushStack.isEmpty()){
                throw new RuntimeException("this queue is empty");
            }else if(popStack.isEmpty()){
                while(!pushStack.empty()){
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.peek();
        }

    }

}
