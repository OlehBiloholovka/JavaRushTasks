package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/


import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            String string = args[0];
//        String string = "12AS08Z";
            if (string == null) {
                System.out.println("incorrect");
            } else

            {
                for (int i = 2; i <= 36; i++) {
                    try{
                        //                long number = Long.parseLong(string.toUpperCase(), i);
                        BigDecimal bigDecimal = new BigDecimal(new BigInteger(string, i));
                        System.out.println(i);
                        return;
                    }catch (Exception e){

                    }
                    if (i == 36){
                        System.out.println("incorrect");
                    }
                }
            }
        } catch (Exception ex){
        }
    }
}