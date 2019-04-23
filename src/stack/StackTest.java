import java.util.Stack;

/**
 * @author xuyh
 * @date 2019/4/23
 */
public class StackTest {
    public static class MyStack{
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;
        public MyStack(){
            dataStack=new Stack<>();
            minStack=new Stack<>();
        }
        public void push(int obj){
            if(minStack.isEmpty()||obj< getMin()){
                minStack.push(obj);
            }else{
                minStack.push(minStack.peek());
            }
            dataStack.push(obj);
        }
        public int pop(){
            if(dataStack.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            minStack.pop();
            return dataStack.pop();
        }
        public int peek(){
            if(dataStack.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            return dataStack.peek();
        }
        public int getMin(){
            if(minStack.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            return minStack.peek();
        }
    }
}
