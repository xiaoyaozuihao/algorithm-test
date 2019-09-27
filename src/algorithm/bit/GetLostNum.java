package bit;

/**
 * 有1~10亿数字的乱序数组，其中少了一个数，怎么快速找到这个丢失的数字呢？
 * 异或补充性质：
 *  even xor (even+1) = 1（偶数异或偶数+1结果值为1）
 *  1 xor even = (even+1) 和 1 xor (even+1) = even
 *
 *  1 xor 2 xor 3...xor n,可以从2开始配对，两两一对
 * 如果n=4k,则可以转化为1 xor((2k-1)个1) xor n =n+1
 * 如果n=4k+1,则((2k+1)个1) xor 1=1
 * 如果n=4k+2,则((2k+1)个1) xor 1 xor n=n
 * 如果n=4k+3,则((2k+2)个1) xor 1=0
 *
 * @author xuyh
 * @date 2019/9/26
 */
public class GetLostNum {
    public static void main(String[] args) {
        int[] nums={1,2,3,5,6,7,8,9,10};
        System.out.println(getLostNum(nums));

    }
    public static int getLostNum(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res ^ nums[nums.length - 1];
    }
}
