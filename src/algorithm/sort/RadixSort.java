package sort;

import java.util.Arrays;

/**
 * 基数排序,基数排序对有负数和0的数列难以进行排序
 * @author xuyh
 * @date 2019/5/30
 */
public class RadixSort {
    public static void radixSort(int[] arr){
        if(arr==null|| arr.length<2){
            return;
        }
        int R=10;//数字范围0-9，基数为10
        int N=arr.length;
        int[] aux=new int[N];
        int[] count=new int[R+1];//长度加1，使count[0]始终为0
        // 以关键字来排序的轮数，由位数最多的数字决定，其余位数少的数字在比较高位时，自动用0进行比较
        // 将数字转换成字符串，字符串的长度就是数字的位数，字符串最长的那个数字也拥有最多的位数
        int W = Arrays.stream(arr).map(s -> String.valueOf(s).length()).max().getAsInt();
        for(int d=0;d<W;d++){
            //1,计算频率，在需要的数组长度上额外加1
            for(int j=0;j<N;j++){
                // 使用加1后的索引，有重复的该位置就自增
                count[digitAt(arr[j],d)+1]++;
            }
            //2.计算元素的开始索引
            for(int i=0;i<R;i++){
                count[i+1]+=count[i];
            }
            //3. 元素按照开始索引分类，用到一个和待排数组一样大临时数组存放数据
            for(int i=0;i<N;i++){
                // 填充一个数据后，自增，以便相同的数据可以填到下一个空位
                aux[count[digitAt(arr[i], d)]++] = arr[i];
            }
            //4.数据回写
            for(int i=0;i<N;i++){
                arr[i]=aux[i];
            }
            //5.重置count[]，以便下一轮统计使用
            for(int i=0;i<count.length;i++){
                count[i]=0;
            }
        }
    }
    // 根据d，获取某个值的个位、十位、百位等，
    // d = 0取出个位，d = 1取出十位，以此类推。对于不存在的高位，用0补
    private static int digitAt(int value,int d){
        return (value/(int)(Math.pow(10,d)))%10;
    }

    public static void radixSort1(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        int maxBits=Arrays.stream(arr).map(i->String.valueOf(i).length()).max().getAsInt();
        radixSort1(arr,0,arr.length-1,maxBits);
    }

    public static void radixSort1(int[] arr,int begin,int end,int digit){
        final int radix=10;
        int i=0,j=0;
        int[] count=new int[radix];
        int[] bucket=new int[end-begin+1];
        for(int d=1;d<radix;d++){
            for(i=0;i<radix;i++){
                count[i]=0;
            }
            for(i=begin;i<=end;i++){
                j=getDigit(arr[i],d);
                count[j]++;
            }
            for(i=1;i<radix;i++){
                count[i]=count[i-1]+count[i];
            }
            for(i=end;i>=begin;i--){
                j=getDigit(arr[i],d);
                bucket[--count[j]]=arr[i];
            }
            for(i=begin,j=0;i<=end;i++,j++){
                arr[i]=bucket[j];
            }
        }

    }

    public static int getDigit(int value,int d){
        return (value/((int)Math.pow(10,d-1)))%10;
    }

    public static void main(String[] args) {
        int[] a = {244, 167, 1234, 321, 29, 98, 1444, 111, 99,99, 6};
//        RadixSort.radixSort(a);
        RadixSort.radixSort1(a);
        System.out.println(Arrays.toString(a));
    }
}
