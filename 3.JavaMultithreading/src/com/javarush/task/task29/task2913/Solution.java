package com.javarush.task.task29.task2913;


import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {

        if (a == b){
            return a + " " + b;
        }

        int min = a < b ? a : b;
        int max = a > b ? a : b;

        int[] resultArray = new int[Math.abs(max-min)+1];

        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = a < b ? min++ : max--;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i : resultArray) {
            stringBuilder.append(i).append(" ");
        }

        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}