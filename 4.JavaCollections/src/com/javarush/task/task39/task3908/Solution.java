package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("wqewe"));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.isEmpty()) return false;
        char[] chars = s.toUpperCase().toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char aChar : chars) {
            if (map.containsKey(aChar)){
                map.put(aChar, map.get(aChar)+1);
            } else {
                map.put(aChar, 1);
            }
        }
        boolean wasOdd = false;
        for (Integer integer : map.values()) {
            if (chars.length % 2 == 0) {
                if (integer % 2 != 0) return false;
            } else {
                if (integer % 2 != 0) {
                    if (wasOdd) return false;
                    wasOdd = true;
                }
            }
        }
        return true;
    }
}
