package leetcode;

/**
 * 一个非空数组所有数中除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * @author xuyh
 * @date 2019/9/26
 */
public class Eg137 {
    public static void main(String[] args) {
        Eg137 eg137 = new Eg137();
        int[] nums = {2,2,3,2};
        System.out.println(eg137.singleNumber(nums));
        System.out.println(eg137.singleNumber1(nums));
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }
            }
            //将每个数拆成32位来看，某一位上1的数目累加和为3的倍数，就置为0，否则为1，最终就找到了只出现一次的数
            if (count % 3 != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }

    public int singleNumber1(int[] nums){
//        int ones = 0, twos = 0, threes = 0;
//        for (int num : nums) {
//            twos |= ones & num;//用Int32位任意一位出现了一次1的结果ones 和当期num与 则同一个位置出现两次的会是1合并到twos 出现一次的保持twos原先的位
//            ones ^= num;//一直异或num 则Int中的某一位出现一次1 ones为1 两次则异或成0 三次还是1 但是会被后续操作清零
//            threes = ones & twos;//ones和twos同时为1时 threes为1
//            ones &= ~threes;//threes对应的位置为1 取反为0 和ones与则将对应位清零
//            twos &= ~threes;
//        }
//        return ones;
        int ones = 0, twos = 0;
        for(int num : nums){
            //当 num = 1时，只当 ones = twos = 0时,将ones置 1，代表出现 3N+1 次；其余置0，
            // 根据twos值分别代表出现3N 次和3N+2 次；
            //当 num = 0时,ones 不变；
            ones = ones ^ num & ~twos;
            //当 num = 1时，只当 ones = twos = 0时将twos置1，代表出现3N+2 次；其余置0，
            // 根据ones值分别代表出现3N 次和3N+1 次。
            //当num=0 时，twos 不变。
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    //参考：https://leetcode-cn.com/problems/single-number-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--31/
    public int singleNumber2(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;
        for (int i : nums) {
            x2 ^= x1 & i;
            x1 ^= i;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }
        return x1;
    }
}
