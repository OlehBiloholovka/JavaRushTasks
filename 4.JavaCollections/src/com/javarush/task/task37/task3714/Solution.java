package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {

        if (!s.matches("^(M{0,3})(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$")){
            throw new  IllegalArgumentException();
        }

        char[] romanDigits = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] arabDigits = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> digitMap = new HashMap<>();
        for (int i = 0; i < romanDigits.length; i++) {
            digitMap.put(romanDigits[i], arabDigits[i]);
        }

        int result = 0;
        char[] chars = s.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            int digit = digitMap.get(chars[i]);
            int digitBefore = i + 1 >= chars.length ? 0 : digitMap.get(chars[i + 1]);
            if (digit < digitBefore) {
                result -= digit;
            } else {
                result += digit;
            }
        }
        return result;
    }
}
