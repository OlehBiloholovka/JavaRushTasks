package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate( "9.10.2017 5:56:45");
        System.out.println();

        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        boolean isDate = false;
        boolean isTime = false;
        String dateString = date;
        String timeString = date;
        DateTimeFormatter dateTimeFormatterForDate = DateTimeFormatter.ofPattern("d.M.y");
        DateTimeFormatter dateTimeFormatterForTime = DateTimeFormatter.ofPattern("H:m:s");

        if (date.contains(" ")){
            String[] strings = date.split(" ");
            dateString = strings[0];
            timeString = strings[1];
            isDate = true;
            isTime = true;
        }else if (date.contains(".")){
            isDate = true;
        }else if (date.contains(":")){
            isTime = true;
        }else {
            return;
        }

        if (isDate){
            LocalDate localDate = LocalDate.parse(dateString, dateTimeFormatterForDate);
            System.out.println(String.format("День: %d", localDate.getDayOfMonth()));
            System.out.println(String.format("День недели: %d", localDate.getDayOfWeek().getValue()));
            System.out.println(String.format("День месяца: %d", localDate.getDayOfMonth()));
            System.out.println(String.format("День года: %d", localDate.getDayOfYear()));
            System.out.println(String.format("Неделя месяца: %d", localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth())));
            System.out.println(String.format("Неделя года: %d", localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear())));
            System.out.println(String.format("Месяц: %d", localDate.getMonthValue()));
            System.out.println(String.format("Год: %d", localDate.getYear()));


//            День: 9
//            День недели: 1
//            День месяца: 9
//            День года: 282
//            Неделя месяца: 3
//            Неделя года: 42
//            Месяц: 10
//            Год: 2017

//            AM или PM: PM
//            Часы: 5
//            Часы дня: 5
//            Минуты: 56
//            Секунды: 45
        }

        if (isTime){
            LocalTime localTime = LocalTime.parse(timeString, dateTimeFormatterForTime);
            System.out.println(String.format("AM или PM: %s", localTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
            System.out.println(String.format("Часы: %d", localTime.get(ChronoField.HOUR_OF_AMPM)));
            System.out.println(String.format("Часы дня: %d", localTime.get(ChronoField.HOUR_OF_DAY)));
            System.out.println(String.format("Минуты: %d", localTime.get(ChronoField.MINUTE_OF_HOUR)));
            System.out.println(String.format("Секунды: %d", localTime.get(ChronoField.SECOND_OF_MINUTE)));

        }






//        LocalDateTime localDateTime = LocalDateTime.parse(date);
//        System.out.println(localDateTime.getDayOfYear());

    }
}
