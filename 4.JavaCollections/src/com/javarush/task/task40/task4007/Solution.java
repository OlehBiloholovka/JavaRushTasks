package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat;
        boolean isDate = false;
        boolean isTime = false;

        if (date.contains(" ")){
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            isDate = true;
            isTime = true;
        } else if (date.contains(".")){
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            isDate = true;
        } else if (date.contains(":")){
            simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            isTime = true;
        } else {
            return;
        }
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (isDate){
            System.out.println(String.format("День: %d", calendar.get(Calendar.DATE)));
            System.out.println(String.format("День недели: %d", calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 :calendar.get(Calendar.DAY_OF_WEEK) - 1));
            System.out.println(String.format("День месяца: %d", calendar.get(Calendar.DAY_OF_MONTH)));
            System.out.println(String.format("День года: %d", calendar.get(Calendar.DAY_OF_YEAR)));
            System.out.println(String.format("Неделя месяца: %d", calendar.get(Calendar.WEEK_OF_MONTH)));
            System.out.println(String.format("Неделя года: %d", calendar.get(Calendar.WEEK_OF_YEAR)));
            System.out.println(String.format("Месяц: %d", calendar.get(Calendar.MONTH) + 1));
            System.out.println(String.format("Год: %d", calendar.get(Calendar.YEAR)));
        }

        if (isTime){
            System.out.println(String.format("AM или PM: %s", calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
            System.out.println(String.format("Часы: %d", calendar.get(Calendar.HOUR)));
            System.out.println(String.format("Часы дня: %d", calendar.get(Calendar.HOUR_OF_DAY)));
            System.out.println(String.format("Минуты: %d", calendar.get(Calendar.MINUTE)));
            System.out.println(String.format("Секунды: %d", calendar.get(Calendar.SECOND)));
        }

    }
}
