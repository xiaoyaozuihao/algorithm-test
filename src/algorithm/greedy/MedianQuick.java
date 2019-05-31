package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 取中位数的算法
 * @author xuyh
 * @date 2019/5/27
 */
public class MedianQuick {
    public static class MedianHolder{
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>((o1,o2)->o2-o1);

        public void modifyHeapSize(){
            if(minHeap.size()==maxHeap.size()+2){
                maxHeap.add(minHeap.poll());
            }
            if(maxHeap.size()==minHeap.size()+2){
                minHeap.add(maxHeap.poll());
            }
        }

        public void addNum(int num){
            if(maxHeap.isEmpty()){
                maxHeap.add(num);
                return;
            }
            if(maxHeap.peek()>=num){
                maxHeap.add(num);
            }else{
                if(minHeap.isEmpty()){
                    minHeap.add(num);
                    return;
                }
                if(minHeap.peek()>num){
                    maxHeap.add(num);
                }else{
                    minHeap.add(num);
                }
            }
            modifyHeapSize();
        }

        public Integer getMedian(){
            int minSize= minHeap.size();
            int maxSize= maxHeap.size();
            if(minSize+maxSize==0){
                return null;
            }
            if(((minSize+maxSize)&1)==0){
                return (minHeap.peek()+maxHeap.peek())>>1;
            }
            return minSize>maxSize?minHeap.peek():maxHeap.peek();
        }
    }
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    //for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNum(arr[j]);
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

    }
}
