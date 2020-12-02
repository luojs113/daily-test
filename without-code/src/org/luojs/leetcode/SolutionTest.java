package org.luojs.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 罗俊生
 * @date 2020/9/27 16:29
 */
public class SolutionTest {

    public static void main(String[] args) {
        int max = maxArea(new int[]{1,4,2,3});
        System.out.println(max);

        int[] leastNums = new int[]{78,65,2,3,4,9,61,12,18,56,23,48,77};
        System.out.println(getLeastNumbers_Solution(leastNums, 5));
    }

    public static int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int ans = 0;
        while (i < j) {
            int area = (height[i]<height[j]?height[i]:height[j]) * (j - i);
            ans = ans<area?area:ans;
            if (height[i] <= height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }

    /**
     * 获取数组中最小的k个数
     *
     * @param input 输入的数组
     * @param k 元素个数
     * @return 最小的k的数列表
     */
    public static ArrayList<Integer> getLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input==null || input.length==0 || input.length<k || k<1) {
            return res;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int e : input) {
            if (maxHeap.size() < k) {
                maxHeap.add(e);
            } else if (maxHeap.peek() > e) {
                maxHeap.poll();
                maxHeap.add(e);
            }
        }
        System.out.println("ahah:" + maxHeap.toString());
        //res.addAll(maxHeap);
        int maxSize = maxHeap.size();
        for (int i=0;i<maxSize;i++) {
            res.add(maxHeap.poll());
        }
        return res;
    }

}
