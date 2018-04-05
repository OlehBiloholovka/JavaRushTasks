package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(1));
        System.out.println(isPowerOfThree(2));
        System.out.println(isPowerOfThree(3));
        System.out.println(isPowerOfThree(4));
        System.out.println(isPowerOfThree(5));
        System.out.println(isPowerOfThree(6));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(27));
    }

    public static boolean isPowerOfThree(int n) {
        return n >= 1 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));

    }
}
