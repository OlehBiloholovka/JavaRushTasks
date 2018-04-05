package com.javarush.task.task22.task2202;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        String[] words = string.split(" ");

        if (words.length < 5) throw new TooShortStringException();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < 5; i++) {
            stringBuilder.append(words[i]);
            if (i != 4) stringBuilder.append(" ");
        }
        return  stringBuilder.toString();



//        int index1 = string.indexOf(" ")+1;
//        int index4 = string.indexOf(" ", string.indexOf(" ", string.indexOf(" ", string.indexOf(" ")+1)+1)+1);
//
//        if (index4 == -1) throw new TooShortStringException();
//
//        Pattern p = Pattern.compile("\\w");
//        Matcher m = p.matcher(string.substring(index4 + 1));
//        int position = -1;
//        if (m.find()) {
//            position = m.start();
//        }
//
//        if (position != -1){
//            index4 += position;
//            return string.substring(index1, index4);
//        }else {
//            return string.substring(index1);
//        }
//
//
////        int firstIndex = -1, lastIndex = -1;
////        for (int i = 0; i < 4; i++) {
////                lastIndex = string.indexOf(" ", lastIndex + 1);
////            if (i == 0){
////                firstIndex = lastIndex + 1;
////            }
////            if (lastIndex == -1) throw new TooShortStringException();
////        }
//
////        Pattern p = Pattern.compile("\\w");
////        Matcher m = p.matcher(string.substring(lastIndex + 1));
////        int position = -1;
////        if (m.find()) {
////            position = m.start();
////        }
////        if (position != -1){
////            lastIndex += position;
////            return string.substring(firstIndex, lastIndex);
////        }else {
////            return string.substring(firstIndex);
////        }
    }

    public static class TooShortStringException extends RuntimeException{

    }
}
