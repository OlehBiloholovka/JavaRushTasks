package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException{
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        List<Dish> dishes = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите название блюда, или 'exit'.");
        String dishString;
//        while (!(dishString = readString()).equals("exit")){
//            boolean isDish = false;
//            for (Dish dish : Dish.values()) {
//                if (dishString.toLowerCase().equals(dish.name().toLowerCase())) {
//                    dishes.add(dish);
//                    isDish = true;
//                }
//            }
//            if (!isDish) writeMessage(String.format("No such dish as \"%s\"!", dishString));
//        }
        dishString = readString();
        if (dishString != null) {
            while (!dishString.equals("exit")){
                boolean isDish = false;
                for (Dish dish : Dish.values()) {
                    if (dishString.toLowerCase().equals(dish.name().toLowerCase())) {
                        dishes.add(dish);
                        isDish = true;
                    }
                }
                if (!isDish) writeMessage(String.format("No such dish as \"%s\"!", dishString));
                dishString = readString();
            }
        }


        return dishes;
    }

}
