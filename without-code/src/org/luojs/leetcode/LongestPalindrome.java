package org.luojs.leetcode;

/**
 * @author 罗俊生
 * @date 2021/7/30 15:08
 * 给你一个字符串 s，找到 s 中最长的回文子串
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "csrabarop";
        String res = longestPalindrome(s);
        System.out.println(res);
    }

    private static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int size = s.length();
        boolean[][] dp = new boolean[size][size];
        int maxlength = 0;
        int left = 0;
        int right = 0;
        for (int i = size-1; i>=0; i--) {
            for (int j = i; j<size; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && j - i + 1 > maxlength) {
                    maxlength = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right+1);
    }
}
