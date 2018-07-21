package com.xzy.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstring3 {

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str1));
    }


    public static int lengthOfLongestSubstring(String s) {
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            for (int j = charArr.length; j >= i; j--) {
                String testStr = s.substring(i, j);
                char[] arr = testStr.toCharArray();

            }
        }
        return 0;
    }


//    private static boolean checkDistinct(char[] charArr){
//
//    }
}
