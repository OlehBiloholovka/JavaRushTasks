package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        List<Throwable> throwables = new ArrayList<>();


        Throwable throwable = e;
        while (throwable != null){
            throwables.add(0, throwable);
            throwable = throwable.getCause();
        }

        throwables.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Exception exception = new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
        Solution solution = new Solution();
        try {
            throw exception;
        } catch (Exception e) {
            solution.uncaughtException(Thread.currentThread(), e);
        }
    }
}
