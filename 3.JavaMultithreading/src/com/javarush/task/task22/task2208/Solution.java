package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map .put("age", null);
        System.out.println(getQuery(map));

    }
    public static String getQuery(Map<String, String> params) {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (Map.Entry<String, String> pair : params.entrySet()){
//            if (pair.getValue() != null){
//                stringBuilder.append(pair.getKey()).append(" = ").append(pair.getValue()).append(" and ");
//            }
//        }
//
//        return stringBuilder.delete(stringBuilder.lastIndexOf(" and "), stringBuilder.length()).toString();


        String result = params.entrySet().stream()       // стрим мапы получается через entrySet
                .filter(e-> Objects.nonNull(e.getValue()))  // пропускаем лишь записи с непустыми значениями
                .map(e->String.format("%s = \'%s\'",e.getKey(),e.getValue())) // для каждой записи формируем строчку
                .collect(Collectors.joining(" and "));   // объединяем все получившиеся строчки через разделитель
        return result;
//        return null;
    }
}
