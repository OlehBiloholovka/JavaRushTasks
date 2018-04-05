package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY");
    public void printAdvertisementProfit(){

        Map<Date, Long> profitMap = StatisticManager.getInstance().getAdvertisementProfit();
        List<Date> dates = new ArrayList<>(profitMap.keySet());
        dates.sort(Comparator.reverseOrder());

        double totalProfit = dates.stream().map(profitMap::get).mapToDouble(profit -> profit / 100.0).sum();
        dates.forEach(date -> ConsoleHelper.writeMessage(String.format("%s - %.2f"
                , simpleDateFormat.format(date)
                , profitMap.get(date) / 100.0)));
        ConsoleHelper.writeMessage(String.format("Total - %.2f", totalProfit));
    }
    public void printCookWorkloading(){
        Map<Date, Map<String, Integer>> cookWorkloading = StatisticManager.getInstance().getCookWorkloading();
        List<Date> dates = new ArrayList<>(cookWorkloading.keySet());
        dates.sort(Comparator.reverseOrder());

        dates.forEach(date -> {
            ConsoleHelper.writeMessage(simpleDateFormat.format(date));
            cookWorkloading.get(date)
                    .forEach((key, value) -> ConsoleHelper.writeMessage(String.format("%s - %d min"
                    , key
                    , value)));
            ConsoleHelper.writeMessage("");
        });
    }

//    4. Реализуй логику методов printActiveVideoSet и printArchivedVideoSet в классе DirectorTablet.
//Используй методы/метод, созданные в предыдущем пункте.
//Сортировать по имени видео-ролика в алфавитном порядке
//Сначала английские, потом русские.
//
//Пример вывода для printActiveVideoSet:
//First Video - 100
//second video - 10
//Third Video - 2
//четвертое видео - 4
//
//Через 50 показов пример вывода для printArchivedVideoSet:
//second video
//Third Video
//четвертое видео
    public void printActiveVideoSet(){
        StatisticAdvertisementManager.getInstance().getActiveVideoSet().forEach(System.out::println);
    }
    public void printArchivedVideoSet(){
        StatisticAdvertisementManager.getInstance().getArchivedVideoSet().forEach(System.out::println);
    }

}
