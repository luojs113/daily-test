package org.luojs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 罗俊生
 * @date 2021/7/30 14:41
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合
 */
public class LetterCombinations {

    //设置全局列表存储最后的结果
    private static List<String> list = new ArrayList<>();

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    private static StringBuilder temp = new StringBuilder();

    public static void main(String[] args) {
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        String digits = "59";
        list = letterCombinations(numString, digits);
        System.out.println(list.toString());
    }

    public static List<String> letterCombinations(String[] numString, String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        //迭代处理
        backTracking(digits, numString, 0);
        return list;
    }

    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    public static void backTracking(String digits, String[] numString, int num) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //c
            backTracking(digits, numString, num + 1);
            //剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }

}
