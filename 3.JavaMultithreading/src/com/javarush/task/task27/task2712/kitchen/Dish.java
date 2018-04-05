package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum  Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int i) {
        duration = i;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString(){
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Dish dish : values()) {
//            String name = dish.name();
//            stringBuilder.append(name).append(", ");
//        }
//        return stringBuilder.toString().trim();
        return Arrays.toString(Dish.values()).substring(1, Arrays.toString(Dish.values()).length()-1);
    }
}
