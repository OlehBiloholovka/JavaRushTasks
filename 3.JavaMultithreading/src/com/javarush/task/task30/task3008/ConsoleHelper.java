package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
//        String result;
//        try {
//            result = bufferedReader.readLine();
//            bufferedReader.close();
//        } catch (IOException e) {
//            System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
//            result = readString();
//        }
//        return result;

        while (true)
        {
            try
            {
                return bufferedReader.readLine();

            }
            catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }

    }

    public static int readInt(){
//        int result;
//        try{
//            result = Integer.valueOf(readString());
//        }catch (NumberFormatException e){
//            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
//            result = readInt();
//        }
//        return result;

        while (true)
        {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
    }


//    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//    public static void writeMessage(String message) {
//        System.out.println(message);
//    }
//
//    public static String readString() {
//
//        while (true)
//        {
//            try
//            {
//                return reader.readLine();
//
//            }
//            catch (IOException e) {writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");}
//        }
//
//    }
//
//    public static int readInt() {
//        while (true)
//        {
//            try {
//                return Integer.parseInt(readString());
//            } catch (NumberFormatException e) {writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");}
//        }
//    }

}
