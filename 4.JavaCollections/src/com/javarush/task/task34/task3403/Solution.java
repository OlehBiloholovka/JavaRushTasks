package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        int i = 2;

        while (i <= n) {
            if ((n % i) == 0) {
                System.out.print(i + " ");
                recursion(n / i);
                break;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion(13);
    }
}
