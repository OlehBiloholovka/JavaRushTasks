package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
            long length = randomAccessFile.length();
            if (number > length) number = (int) length;
            randomAccessFile.seek(number);
            randomAccessFile.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
