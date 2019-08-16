package sort;

import util.ArrayUtils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author xuyh
 * @description: 多线程 fork join 实现累加
 * @date 2019/8/13
 */
public class ForkJoinCountDemo {

    public static void main(String[] args)  {
        int[] arr = ArrayUtils.getIntArrays(300000000,100000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int j=0;j<10;j++) {
            long c1 = System.currentTimeMillis();
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                count = count + arr[i];
            }
            long c2 = System.currentTimeMillis();
            System.out.println("普通累加耗时" + (c2 - c1) + "毫秒, count:" + count);
            long c3 = System.currentTimeMillis();
            Integer count1=forkJoinPool.submit(new SumTask(arr, 0, arr.length)).join();
            long c4 = System.currentTimeMillis();
            System.out.println("forkjoin累加耗时" + (c4 - c3) + "毫秒, count:" + count1);
        }
    }

    static class SumTask extends RecursiveTask<Integer> {
        private static int THRESHOLD = 500000;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex <= THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i < toIndex; i++) {
                    count = count + src[i];
                }
                return count;
            } else {
                int mid = fromIndex + ((toIndex - fromIndex) >> 1);
                SumTask leftTask = new SumTask(src, fromIndex, mid);
                SumTask rightTask = new SumTask(src, mid, toIndex);
                invokeAll(leftTask, rightTask);
                return leftTask.join() + rightTask.join();
            }
        }
    }

}
