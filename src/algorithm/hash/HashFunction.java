package hash;

/**
 * @author xuyh
 * @description:
 * @date 2019/8/22
 */
public class HashFunction {
    public static void main(String[] args) {
        //int占用4个字节，所有一共是4*8*1000=32000位
        int[] arr=new int[1000];
        int index=30000;
        int intIndex=index/32;
        int bitIndex=intIndex%32;
        System.out.println(intIndex);
        System.out.println(bitIndex);
        arr[intIndex]=arr[intIndex]|(1<<bitIndex);
        System.out.println(arr[intIndex]);
    }
}
