package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
//        Set<Long> resultSet = new HashSet<>();
//        strings.forEach(s -> resultSet.add(shortener.getId(s)));
//        return resultSet;
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
//        Set<String> resultSet = new HashSet<>();
//        keys.forEach(aLong -> resultSet.add(shortener.getString(aLong)));
//        return resultSet;
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
//        Set<String> stringSet = new HashSet<>();
//        for (long i = 0; i < elementsNumber; i++) {
//            stringSet.add(Helper.generateRandomString());
//        }
        Set<String> stringSet = LongStream
                .range(0, elementsNumber)
                .mapToObj(i -> Helper.generateRandomString())
                .collect(Collectors.toSet());

        Shortener shortener = new Shortener(strategy);

        long timeIds = new Date().getTime();
        Set<Long> ids = getIds(shortener, stringSet);
        System.out.println(new Date().getTime() - timeIds);

        long timeStrings = new Date().getTime();
        Set<String> strings = getStrings(shortener, ids);
        System.out.println(new Date().getTime() - timeStrings);

        if (stringSet.equals(strings)) {
            System.out.println("Тест пройден.");
        } else {
            System.out.println("Тест не пройден.");
        }



        /*
        * 6.2.3.4. Замерять и выводить время необходимое для отработки метода getIds
         * для заданной стратегии и заданного множества элементов. Время вывести
         * в миллисекундах. При замере времени работы метода можно пренебречь
         * переключением процессора на другие потоки, временем, которое тратится
         * на сам вызов, возврат значений и вызов методов получения времени (даты).
         * Замер времени произведи с использованием объектов типа Date.
         *
         * 6.2.3.5. Замерять и выводить время необходимое для отработки метода
         * getStrings для заданной стратегии и полученного в предыдущем пункте
         * множества идентификаторов.
         *
         * 2.3.6. Сравнивать одинаковое ли содержимое множества строк, которое
         * было сгенерировано и множества, которое было возвращено методом getStrings.
         * Если множества одинаковы, то выведи "Тест пройден.", иначе "Тест не пройден.".
         */


    }
}
