package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        //...
        String fileName = null;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            fileName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileName != null){
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

                List<String> stringList = new ArrayList<>();
                bufferedReader.lines().forEach(s -> stringList.addAll(Arrays.asList(s.split(" "))));

                String[] words = new String[stringList.size()];
                words = stringList.toArray(words);
                Arrays.sort(words);

                System.out.println(getLine(words));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static StringBuilder getLine(String... words) {
        if (words != null){

            for (int i = 0; i < words.length; i++) {
                for (int j = i+1; j < words.length; j++) {

                    if (words[j].toLowerCase().startsWith(words[i].substring(words[i].length()-1))){
                        String word = words[i+1];
                        words[i+1] = words[j];
                        words[j] = word;
                        break;
                    }
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                stringBuilder.append(words[i]);
                if (i < words.length-1){
                    stringBuilder.append(" ");
                }

            }
            return stringBuilder;

        }else {
            return new StringBuilder();
        }

//          return null;
    }
}
