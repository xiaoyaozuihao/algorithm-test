package other;

import util.BaseUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xuyh
 * @description: 随时找到数据流中的中位数
 * @date 2019/8/23
 */
public class MedianHolder {
    public static void main(String[] args) {
        MedianHolder medianHolder = new MedianHolder();
        boolean err = false;
        for (int i = 0; i < 1000; i++) {
            medianHolder.minHeap.clear();
            medianHolder.maxHeap.clear();
            int[] arr = BaseUtil.generateRandomArray(100, 100);
            Integer median = getMedianOfArray(arr);
            for (int j = 0; j < arr.length; j++) {
                medianHolder.addNum(arr[j]);
            }
            Integer median1 = medianHolder.getMadian();
            if (median!=null&&!median.equals(median1)) {
                err = true;
                BaseUtil.printArray(arr);
                break;
            }
        }
        System.out.println(err ? "ho no" : "today is a beautiful day ^_^");
    }

    public static Integer getMedianOfArray(int[] arr) {
        if(arr==null||arr.length==0){
            return null;
        }
        int[] arr1 = BaseUtil.copyArray(arr);
        Arrays.sort(arr1);
        int mid = (arr1.length - 1) / 2;
        if ((arr1.length & 1) == 0) {
            return (arr1[mid] + arr1[mid + 1]) / 2;
        }
        return arr1[mid];
    }

    //默认小根堆
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public void addNum(Integer num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }
        if (maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            if (minHeap.isEmpty()) {
                minHeap.add(num);
                return;
            }
            if (minHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
        }
        modifyTwoHeapsSize();
    }

    private void modifyTwoHeapsSize() {
        if (maxHeap.size() == minHeap.size() + 2) {
            minHeap.add(maxHeap.poll());
        }
        if (minHeap.size() == maxHeap.size() + 2) {
            maxHeap.add(minHeap.poll());
        }
    }

    public Integer getMadian() {
        int maxHeapSize = maxHeap.size();
        int minHeapSize = minHeap.size();
        if (maxHeapSize + minHeapSize == 0) {
            return null;
        }
        Integer maxPeek = maxHeap.peek();
        Integer minPeek = minHeap.peek();
        if (((maxHeapSize + minHeapSize) & 1) == 0) {
            return (maxPeek + minPeek) / 2;
        }
        return maxHeapSize > minHeapSize ? maxPeek : minPeek;
    }
}
