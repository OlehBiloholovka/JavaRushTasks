package com.javarush.task.task26.task2601;


import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution{



    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here

        double median;

        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        if (array.length %2 == 1){
            median = array[array.length / 2];
        }else {
            median = (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        }

        Arrays.sort(array, Comparator.comparingInt(o -> Math.abs((int) (o - median))));
        return array;
    }
}
