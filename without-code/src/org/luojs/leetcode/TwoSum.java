package org.luojs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 罗俊生
 * @date 2021/7/30 14:41
 * 给定一个整数数组 nums 和一个目标值 target
 * 请你在该数组中找出和为目标值的那 两个 整数
 * TwoSum并返回他们的数组下标
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2,7,10,15};
        int target = 17;
        int[] res = twoSum(nums, target);
        System.out.println(res[0] + " : " + res[1]);
    }

    private static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i=0;i<nums.length;i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[1] = i;
                res[0] = map.get(temp);
                break;
            }
            map.put(nums[i], i);
        }

        return res;
    }

}
