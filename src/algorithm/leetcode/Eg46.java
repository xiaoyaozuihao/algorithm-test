package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author: xuyh
 * @create: 2019/9/16
 **/
public class Eg46 {
    public static void main(String[] args) {
        Eg46 eg46 = new Eg46();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = eg46.permute(nums);
        System.out.println(permute);
        System.out.println(eg46.permute1(nums));
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(nums, res, list, visited);
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            list.add(nums[i]);
            helper(nums, res, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        process(nums, 0, res);
        return res;
    }

    public void process(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int j : nums) {
                list.add(j);
            }
            res.add(list);
            return;
        }
        for (int m = i; m < nums.length; m++) {
            swap(nums, m, i);
            process(nums, i + 1, res);
            swap(nums, m, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
//        int tmp=nums[i];
//        nums[i]=nums[j];
//        nums[j]=tmp;
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}
