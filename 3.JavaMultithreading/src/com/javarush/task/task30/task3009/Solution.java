package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number){
        Set<Integer> resultSet = new HashSet<>();

        try{
            int integer = Integer.parseInt(number);

            for (int i = 2; i <= 36; i++) {

                int tempInteger = integer;
                StringBuilder result = new StringBuilder();
                int div;
                do {
                    div = tempInteger / i;
                    int mod = tempInteger % i;
                    if (mod > 9){
                        result.append((char) (64 + (mod - 9)));
                    }else {
                        result.append(mod);
                    }

                    tempInteger = div;
                } while (div > 0);

                if (div != 0){
                    result.append(div);
                }

                if (result.toString().equals(result.reverse().toString())){
                    resultSet.add(i);
                }

            }


        }catch (NumberFormatException e){

        }

        return resultSet;
    }
}