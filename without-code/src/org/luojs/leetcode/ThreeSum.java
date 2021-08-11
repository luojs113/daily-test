package org.luojs.leetcode;

import java.util.*;

/**
 * @author 罗俊生
 * @date 2021/7/30 14:41
 * 给定一个整数数组 nums 和一个目标值 target
 * 判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * 请你找出所有满足条件且不重复的三元组
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int target = 0;
        List<List<Integer>> res = threeSum(nums, target);
        System.out.println(res.toString());
    }

    private static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i=0; i<nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while(right>left) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(right>left && nums[right] == nums[right-1]) {
                        right--;
                    }
                    while(right>left && nums[left] == nums[left+1]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }
        }

        return result;
    }

}
