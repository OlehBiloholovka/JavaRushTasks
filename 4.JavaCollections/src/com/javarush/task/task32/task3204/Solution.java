package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        boolean isInt = false;
        boolean isUpperCase = false;
        boolean isLowerCase = false;
        int[] randomInts = getRandom(48, 57);
        int[] randomUpperCase = getRandom(65, 90);
        int[] randomLowerCase = getRandom(97, 122);
        int[][] arrays = {randomInts, randomUpperCase, randomLowerCase};
        byte[] result = new byte[8];

//        for (int i = 3; i < result.length; i++) {
//            int randomArray = new Random().ints(0, 3).findFirst().getAsInt();
//            result[i] = (byte) arrays[randomArray][i];
//        }
        while (true) {
            for (int i = 0; i < result.length; i++) {
                int randomArray = new Random().ints(0, 3).findFirst().getAsInt();
                switch (randomArray) {
                    case 0:
                        isInt = true;
                        break;
                    case 1:
                        isUpperCase = true;
                        break;
                    case 2:
                        isLowerCase = true;
                }
                result[i] = (byte) arrays[randomArray][i];
            }
            if (isInt && isUpperCase && isLowerCase) break;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(result);
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return byteArrayOutputStream;
    }

    private static int[] getRandom(int fromByte, int tillByte){
        return new Random().ints(8, fromByte, (tillByte + 1)).toArray();
    }
}