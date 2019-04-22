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
        private Queue<Integer> queue;
        private Queue<Integer> help;
        public TwoQueuesStack(){
            queue =new LinkedList<>();
            help=new LinkedList<>();
        }
        public void push(int obj){
            queue.add(obj);
        }
        public int pop(){
            if(queue.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }
            while(queue.size()>1){
                help.add(queue.poll());
            }
            int res= queue.poll();
            swap();
            return res;
        }

        public int peek(){
            if(queue.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }
            while(queue.size()!=1){
                help.add(queue.poll());
            }
            int res= queue.poll();
            help.add(res);
            swap();
            return res;

        }

        public void swap(){
            Queue tmp= queue;
            queue =help;
            help=tmp;
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
