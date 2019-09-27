package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 3, 6, 8, 9, 3, 1};
        System.out.println(comparator(arr));
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eors = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            eors[i] = eor;
        }
        int[] mosts = new int[arr.length];
        mosts[0] = arr[0] == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            mosts[i] = eors[i] == 0 ? 1 : 0;
            for (int j = 0; j < i; j++) {
                if ((eors[i] ^ eors[j]) == 0) {
                    mosts[i]=Math.max(mosts[i],mosts[j]+1);
                }
            }
            mosts[i]=Math.max(mosts[i],mosts[i-1]);
        }
        return mosts[arr.length - 1];
    }
}