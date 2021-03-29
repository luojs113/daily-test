package org.luojs.leetcode;

/**
 * @author 罗俊生
 * @date 2021/3/25 18:02
 */
public class KuoHaoTest {

    public static void main(String[] args) {
        String str = "((()))(((())))";
        int deep = calDeep(str);
        System.out.println("深度：" + deep);
    }

    private static int calDeep(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        int count = 0;
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if (a == '(') {
                count++;
            } else {
                max = Math.max(max, count);
                count--;
            }
        }
        return max;
    }
}
