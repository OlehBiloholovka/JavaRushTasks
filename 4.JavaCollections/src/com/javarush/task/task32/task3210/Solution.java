package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fileName= args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
            randomAccessFile.seek(number);

            byte[] buffer = new byte[text.getBytes().length];
            randomAccessFile.read(buffer, 0, text.getBytes().length);
            String fileText = convertByteToString(buffer);
//            if (fileText.equals(text))

            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(String.valueOf(fileText.equals(text)).getBytes());



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String convertByteToString(byte readBytes[]){
        return new String(readBytes);
    }
}
