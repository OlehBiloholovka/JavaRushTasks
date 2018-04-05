package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please type in a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please type in first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please type in second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
//        String binaryString = Long.toBinaryString(number);
//        char[] chars = binaryString.toCharArray();
//        System.out.println(chars);
//        i = chars.length - i - 1;
//        j = chars.length - j - 1;
//        chars[i] ^= chars[j];
//        chars[j] ^= chars[i];
//        chars[i] ^= chars[j];
//        System.out.println(chars);
//        return Long.parseLong(String.valueOf(chars), 2);
        long a = number >> i & 1;
        long b = number >> j & 1;

        if (a != b)
            number = number & ~((1 << i) | (1 << j)) | (a << j) | (b << i);

        return number;
    }
}
