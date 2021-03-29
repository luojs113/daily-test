package org.luojs.leetcode;

/**
 *
 * @author 罗俊生
 * @date 2021/3/25 16:56
 */
public class HuiWenCheck {

    private static int index = 0;
    private static int length = 0;

    public static void main(String[] args) {
        String strA = "AmanaplanacanalPanama";
        String strB = "A m,an,, a p,lan, a can,al,,: Pan,am a";
        boolean flagA = isPalindromeA(strA);
        boolean flagB = isPalindromeB(strB);
        System.out.println(flagA + "\n" + flagB);

        String strC = "anuiopoiulkj";
        String resC = longestPalindrome(strC);
        System.out.println(resC);
    }

    private static boolean isPalindromeA(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length() / 2; i++) {
            char a = str.charAt(i);
            char b = str.charAt(str.length() - 1 - i);
            if (Character.toUpperCase(a) != Character.toUpperCase(b)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindromeB(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int i = 0, r = str.length() - 1;
        int count = 0;
        while (i < r) {
            count++;
            if (!Character.isLetterOrDigit(str.charAt(i)) && !Character.isLetterOrDigit(str.charAt(r))) {
                i++;
                r--;
            } else if (!Character.isLetterOrDigit(str.charAt(i))) {
                i ++;
            } else if (!Character.isLetterOrDigit(str.charAt(r))) {
                r --;
            } else {
                char a = str.charAt(i);
                char b = str.charAt(r);
                if (Character.toUpperCase(a) != Character.toUpperCase(b)) {
                    return false;
                }
                i++;
                r--;
            }
        }
        System.out.println("cishu:" + count);
        return true;
    }

    private static String longestPalindrome(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            palindromeHelper(str, i, i);
            palindromeHelper(str, i, i + 1);
        }
        return str.substring(index, index + length);
    }

    private static void palindromeHelper(String str, int i, int r) {
        while (i>=0 && r<str.length() && str.charAt(i) == str.charAt(r)) {
            i--;
            r++;
        }
        if (length < r -i-1) {
            index = i + 1;
            length = r -i -1;
        }
    }

}
