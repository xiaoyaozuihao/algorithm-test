package stack;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现基本功能的基础上，再实现返回栈中最小元素的操作。要求push,pop,getMin的时间复杂度都是O(1)
 * @author xuyh
 * @date 2019/4/22
 */
public class GetMinStack {
    public static class MyStack{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        public MyStack(){
            stackData=new Stack<>();
            stackMin=new Stack<>();
        }
        public void push(int obj){
            if(stackMin.isEmpty()){
                stackMin.push(obj);
            }else if(obj<getMin()){
                stackMin.push(obj);
            }
            stackData.push(obj);
        }

        public int pop(){
            if(stackData.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }
            int value = stackData.pop();
            if(value==getMin()){
                stackMin.pop();
            }
            return value;
        }

        public int getMin(){
            if(stackMin.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }
            return stackMin.peek();
        }
    }
    public static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        public MyStack1(){
            stackData=new Stack<>();
            stackMin=new Stack<>();
        }
        public void push(int obj){
            if(stackMin.isEmpty()){
                stackMin.push(obj);
            }else if(obj < getMin()){
                stackMin.push(obj);
            }else {
                stackMin.push(stackMin.peek());
            }
            stackData.push(obj);
        }

        public int pop(){
            if(stackData.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }
            stackMin.pop();
            return stackData.pop();
        }
        public int getMin(){
            if(stackMin.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }
            return stackMin.peek();
        }
    }
}
