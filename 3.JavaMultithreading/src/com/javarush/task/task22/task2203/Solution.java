package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        int index1 = string.indexOf("\t");
        if (index1 == -1) throw new TooShortStringException();
        int index2 = string.indexOf("\t", index1 +1);
        if (index2 == -1 ) throw new TooShortStringException();
        return string.substring(index1+1, index2);

//        String[] strings = string.split("\t");
//        if (strings.length < )
//        return null;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException{
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
