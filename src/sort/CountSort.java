/**
 * 计数排序，需要占用大量空间，它仅适用于数据比较集中的情况，比如 [0~100]，[10000~19999] 这样的数据。
 * @author xuyh
 * @date 2019/4/13
 */
public class CountSort {

    public static void countSort(int[] array){
        if(array==null||array.length<2){
            return;
        }
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<array.length;i++){
            min=Math.min(array[i],min);
            max=Math.max(array[i],max);
        }
        int[] bucket=new int[max-min+1];//如果数组中的数不集中，会浪费大量的桶
        for(int i=0;i< array.length;i++){
            bucket[array[i]-min]++;
        }
        for(int j=0,i=0;j<bucket.length;j++){
            while(bucket[j]-->0){
                array[i++]=j+min;
            }
        }
    }

    public static void main(String[] args) {
        BaseUtil.testTemplate("CountSort","countSort");
    }
}
