package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];


        try(FileInputStream fileInputStream = new FileInputStream(fileName1);
            FileOutputStream fileOutputStream = new FileOutputStream(fileName2)) {
            while (fileInputStream.available() > 0){
                byte[] buffer = new byte[1000];
                fileInputStream.read(buffer);
                fileOutputStream.write(new String(buffer, Charset.forName("windows-1251")).getBytes("utf-8"));
            }
        }

//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName1));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2))){
//            while (bufferedReader.ready()){
//                byte[] buffer = new byte[1000];
//                bufferedReader.read(buffer);
//
//
//                bufferedWriter.write(new String(bufferedReader.readLine().getBytes(Charset.forName("windows-1251")), Charset.forName("utf-8")));
//                bufferedWriter.newLine();
//            }
////            bufferedReader.lines().forEach(str -> {
////                try {
////                    bufferedWriter.write(new String(str.getBytes(Charset.forName("windows-1251")), Charset.forName("utf-8")));
////                    bufferedWriter.newLine();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            });
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }


    }
}
