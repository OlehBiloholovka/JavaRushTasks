package com.javarush.task.task40.task4009;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
        System.out.println(weekDayOfBirthday("1.12.2015", "2016"));
    }

    public static String weekDayOfBirthday(String birthday, String year) {
        LocalDate localDate = LocalDate
                .parse(birthday, DateTimeFormatter.ofPattern("d.M.yyyy")).with(Year.parse(year));

        return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);

    }
}
