package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
//        return false;
        if (telNumber == null || telNumber.isEmpty()) return false;
        return telNumber.matches("(\\+\\d{2})?((\\((\\d{3})\\))|\\d{3})(\\d){3}(\\-?\\d{2}){2}");

    }

    public static void main(String[] args) {
        String[] strings = {"+380501234567", "+38(050)1234567", "+38050123-45-67", "050123-4567", "+38)050(1234567"
                , "+38(050)1-23-45-6-7", "050ххх4567", "050123456", "(0)501234567"};
        for (String string : strings) {
            System.out.println(string+ " - " + checkTelNumber(string));
        }
    }
}
