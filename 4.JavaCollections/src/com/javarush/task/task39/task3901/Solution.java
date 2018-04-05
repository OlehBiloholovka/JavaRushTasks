package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int maxCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (!stringBuilder.toString().contains(String.valueOf(aChar))) {
                stringBuilder.append(aChar);
                if (maxCount < stringBuilder.length()) maxCount = stringBuilder.length();
            } else {
                int j = lengthOfLongestUniqueSubstring(s.substring(s.indexOf(aChar) + 1));
                if (maxCount < j) {
                    maxCount = j;
                }
                break;
            }
        }
        return maxCount;
    }
}
