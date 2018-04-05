package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("qwer", "qwert"));
        System.out.println(isOneEditAway("qwer", "wer"));
        System.out.println(isOneEditAway("qer", "qwer"));
        System.out.println(isOneEditAway("qwer", "qqer"));
        System.out.println(isOneEditAway("qwer", "qewr"));
        System.out.println(isOneEditAway("qwe", "qwerty"));
        System.out.println(isOneEditAway("e", ""));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null) return false;
        if (first.equals(second)) return true;
        if (Math.abs(first.length()-second.length()) > 1) return false;

        char[] chars1 = first.toCharArray();
        char[] chars2 = second.toCharArray();
        boolean wasBadChar = false;

        int length = chars1.length < chars2.length ? chars1.length : chars2.length;

        for (int i = 0, j = 0; i < length; i++, j++) {
            System.out.println(i +" - "+j);
            if (chars1[i] != chars2[j]){
                if (wasBadChar) return false;
                if (chars1.length > chars2.length){
                    j--;
                }else if (chars1.length < chars2.length){
                    i--;
                }
                wasBadChar = true;
            }
        }

        return true;
    }
}
