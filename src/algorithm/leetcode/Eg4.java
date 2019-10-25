package leetcode;

/**
 * 寻找两个有序数组的中位数
 *
 * @author xuyh
 * @date 2019/10/10
 */
public class Eg4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 8, 9};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArrays1(nums1, nums2));
        System.out.println(findMedianSortedArrays2(nums1, nums2));
        System.out.println(findMedianSortedArrays4(nums1, nums2));
        System.out.println(findMedianSortedArrays5(nums1, nums2));
    }

    //最容易想到的解法，归并排序后计算中位数，时间复杂度O(m+n)
    public double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
//        if (m == 0) {
//            if (n % 2 == 0) {
//                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
//            } else {
//                return nums2[n / 2];
//            }
//        }
//        if (n == 0) {
//            if (m % 2 == 0) {
//                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
//            } else {
//                return nums1[m / 2];
//            }
//        }
//        int count = 0;
//        int i = 0, j = 0;
//        while (count != (m + n)) {
//            if (i == m) {
//                while (j != n) {
//                    nums[count++] = nums2[j++];
//                }
//                break;
//            }
//            if (j == n) {
//                while (i != m) {
//                    nums[count++] = nums1[i++];
//                }
//                break;
//            }
//            if (nums1[i] < nums2[j]) {
//                nums[count++] = nums1[i++];
//            } else {
//                nums[count++] = nums2[j++];
//            }
//        }
//        if (count % 2 == 0) {
//            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
//        } else {
//            return nums[count / 2];
//        }
        //更精简的写法
        int p = 0, q = 0, index = 0;
        while (p < m && q < n) {
            nums[index++] = nums1[p] < nums2[q] ? nums1[p++] : nums2[q++];
        }
        while (p < m) {
            nums[index++] = nums1[p++];
        }
        while (q < n) {
            nums[index++] = nums2[q++];
        }
        if (index % 2 == 0) {
            return (nums[index / 2 - 1] + nums[index / 2]) / 2.0;
        } else {
            return nums[index / 2];
        }
    }

    //二分查找，将num1和nums2划分成相同长度的两部分，即i+j=m-i+n-j+1,i~(0-m), 则j=(m+n+1)/2-i
    //只要满足B[j−1]≤A[i] 以及 A[i-1] ≤ B[j]条件即可
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        //保证数组总长度为奇数时，中位数落在左边
        int k = (n1 + n2 + 1) / 2;
        int left = 0;
        int right = n1;
        while (left < right) {//左闭右开的条件，所以二分时也应遵循，right不需要-1
            int m1 = left + (right - left) / 2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2 - 1])
                left = m1 + 1;
            else
                right = m1;
        }
        //找到正确的切分位置
        int m1 = left;
        int m2 = k - left;
        //边界判断
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5;
    }

    //二分查找，官方解答，类似上面的二分
    public double findMedianSortedArrays3(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            //直接在循环中添加了边界条件判断
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                //边界处理
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                //之前的切分方式保证了数组总长度为奇数时，中位数会在左边
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                int minRight = 0;
                //边界处理
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    //O(m+n)的直观解法
    //用 len 表示合并后数组的长度，如果是奇数，我们需要知道第 （len + 1）/2 个数就可以了，
    // 如果遍历的话需要遍历 int ( len / 2 ) + 1 次。如果是偶数，我们需要知道第 len/2和len/2 + 1 个数
    // 也是需要遍历 len/2+1 次。所以遍历的话，奇数和偶数都是 len / 2 + 1 次。
    public static double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        //奇数需要最后一次遍历的结果就可以了，偶数需要最后一次和上一次遍历的结果。
        // 所以我们用两个变量 left 和 right ，right 保存当前循环的结果，
        // 在每次循环前将 right 的值赋给 left 。这样在最后一次循环的时候，
        // left 将得到 right 的值，也就是上一次循环的结果，接下来 right 更新为最后一次的结果。
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;//双指针
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    //第k小的数的解法
    //两个有序数组求中位数，问题一般化为，求两个有序数组的第k个数，当k = (m+n)/2时为原问题的解
    //一般的情况 A[1] ，A[2] ，A[3]，A[k/2] ... ，B[1]，B[2]，B[3]，B[k/2] ... ，
    // 如果 A[k/2]<B[k/2] ，那么A[1]，A[2]，A[3]，A[k/2]都不可能是第 k 小的数字。
    // A数组中比 A[k/2] 小的数有 k/2-1 个，B 数组中，B[k/2] 比 A[k/2] 大，
    // 假设 B[k/2] 前边的数字都比 A[k/2] 小，也只有 k/2-1 个，所以比 A[k/2] 小的数字最多有 k/2-1+k/2-1=k-2个，
    // 所以 A[k/2] 最多是第 k-1 小的数。而比 A[k/2] 小的数更不可能是第 k 小的数了，所以可以把它们排除。
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    public double findMedianSortedArrays22(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //处理任何一个nums为空数组的情况
        if (m == 0) {
            if (n % 2 != 0)
                return 1.0 * nums2[n / 2];
            return (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
        }
        if (n == 0) {
            if (m % 2 != 0)
                return 1.0 * nums1[m / 2];
            return (nums1[m / 2] + nums1[m / 2 - 1]) / 2.0;
        }
        int total = m + n;
        //总数为奇数，找第 total / 2 + 1 个数
        if ((total & 1) == 1) {
            return find_kth(nums1, 0, nums2, 0, total / 2 + 1);
        }
        //总数为偶数，找第 total / 2 个数和第total / 2 + 1个数的平均值
        return (find_kth(nums1, 0, nums2, 0, total / 2) + find_kth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
    }

    //寻找a 和 b 数组中，第k个数字
    double find_kth(int[] a, int a_begin, int[] b, int b_begin, int k) {
        //当a 或 b 超过数组长度，则第k个数为另外一个数组第k个数
        if (a_begin >= a.length)
            return b[b_begin + k - 1];
        if (b_begin >= b.length)
            return a[a_begin + k - 1];
        //k为1时，两数组最小的那个为第一个数
        if (k == 1)
            return Math.min(a[a_begin], b[b_begin]);
        int mid_a = Integer.MAX_VALUE;
        int mid_b = Integer.MAX_VALUE;
        //mid_a / mid_b 分别表示 a数组、b数组中第 k / 2 个数
        if (a_begin + k / 2 - 1 < a.length)
            mid_a = a[a_begin + k / 2 - 1];
        if (b_begin + k / 2 - 1 < b.length)
            mid_b = b[b_begin + k / 2 - 1];
        //如果a数组的第 k / 2 个数小于b数组的第 k / 2 个数，表示总的第 k 个数位于 a的第k / 2个数的后半段，或者是b的第 k / 2个数的前半段
        //由于范围缩小了 k / 2 个数，此时总的第 k 个数实际上等于新的范围内的第 k - k / 2个数，依次递归
        if (mid_a < mid_b)
            return find_kth(a, a_begin + k / 2, b, b_begin, k - k / 2);
        //否则相反
        return find_kth(a, a_begin, b, b_begin + k / 2, k - k / 2);
    }

    //关于c2 = m + n - c1;的个人理解：
    //1、两个虚拟数组[#2#3#5][#1#4#7#9#]合并之后的数组A，长度为2*(m+n)+2，多余了一个#，忽略掉这个#，有效长度为2*(m+n)+1；
    //2、求数组A的中位数转换成了求2*(m+n)+1长度数组的中位数，也就是求第m+n+1位置的元素的值；
    //3、c1、c2都是下标，从0开始，那么减去1，就是c2=m+n-c1;
    public static double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        //我们目前是虚拟加了'#',所以数组1是2*n+1长度,那么最大值坐标就是2*n
        // c1 + c2 = m + n的理解：注意填充#号后有2m+2n+2个元素，那么一半就是m+n+1,所以坐标就是[0, m+n]，所以c1 + c2 = m + n
        // Ci 为第i个数组的割,可能割在数字上，也可能割在'#'上，如果割在数字上，就让lmax=rmin=此数字，数组映射关系可以满足这一点。
        // LMaxi为第i个数组割后的左元素。RMini为第i个数组割后的右元素。
        int LMax1 = 0, LMax2 = 0, RMin1 = 0, RMin2 = 0, c1, c2, lo = 0, hi = 2 * n;
        while (lo <= hi) {
            c1 = lo + (hi - lo) / 2;
            c2 = m + n - c1;
            LMax1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1 - 1) / 2];
            RMin1 = (c1 == 2 * n) ? Integer.MAX_VALUE : nums1[c1 / 2];
            LMax2 = (c2 == 0) ? Integer.MIN_VALUE : nums2[(c2 - 1) / 2];
            RMin2 = (c2 == 2 * m) ? Integer.MAX_VALUE : nums2[c2 / 2];
            if (LMax1 > RMin2)
                hi = c1 - 1;
            else if (LMax2 > RMin1)
                lo = c1 + 1;
            else
                break;
        }
        return (Math.max(LMax1, LMax2) + Math.min(RMin1, RMin2)) / 2.0;
    }

    //最简洁的写法
    public static double findMedianSortedArrays5(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays5(nums2, nums1);
        }
        int cut1 = 0, cut2 = 0, cutL = 0, cutR = m;
        while (cut1 <= m) {
            //数组长度是奇数，切在中位数上，偶数的话切在下中位数
            cut1 = cutL + (cutR - cutL) / 2;
            cut2 = (m + n) / 2 - cut1;
            double l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double r1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            double r2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];
            if (l1 > r2) {
                cutR = cut1 - 1;
            } else if (l2 > r1) {
                cutL = cut1 + 1;
            } else {
                if ((m + n) % 2 == 0) {
                    l1 = l1 > l2 ? l1 : l2;
                    r1 = r1 < r2 ? r1 : r2;
                    return (l1 + r1) / 2;
                } else {
                    //当是奇数时，中位数出现在r1和r2中
                    r1 = r1 < r2 ? r1 : r2;
                    return r1;
                }
            }
        }
        return -1;
    }
}
