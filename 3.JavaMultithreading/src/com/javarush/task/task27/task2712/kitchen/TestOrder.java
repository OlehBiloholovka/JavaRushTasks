package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
//        г) переопредели initDishes в классе-наследнике TestOrder.
// Сделай инициализацию случайным набором блюд.
        Dish[] dishValues = Dish.values();

        List<Dish> dishList = new ArrayList<>();
        for (int i = 0; i < 1 + (int) (Math.random() * 10); i++) {
            dishList.add(dishValues[(int) (Math.random() * (dishValues.length - 1))]);
        }

        dishes = dishList;
    }
}
