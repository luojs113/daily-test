package org.luojs.sorttest;

import java.util.Arrays;

/**
 * @author 罗俊生
 * @date 2019/7/6 17:10
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = new int[]{2,8,5,9,1,4,7,3,6};
        QSort(a, 0, a.length-1);
        System.out.print(Arrays.toString(a));
    }

    public static void QSort(int[] a, int left, int right) {
        if(left >= right) {
            return;
        }
        //三数中值分割法选取枢纽元
        int base = median3(a, left, right);
        int i = left;
        int j = right - 1;
        while(i < j) {
            while(i < j && base > a[++i]) {}
            while(i < j && base < a[--j]) {}
            if(i < j) {
                swap(a, i, j);
            }
        }
        swap(a, i, right - 1);

        QSort(a, left, i - 1);
        QSort(a, i + 1, right);

    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    //三数中值分割法
    private static int median3(int[] a,int i,int j) {
        //对三个数进行排序
        int m = (i + j) >> 1;
        if (a[m] < a[i]) {
            swap(a, i, m);
        }
        if (a[j] < a[i]) {
            swap(a, i, j);
        }
        if (a[j] < a[m]) {
            swap(a, j, m);
        }
        //将枢纽元放在j - 1;
        swap(a, m, j - 1);
        return a[j - 1];
    }

}
