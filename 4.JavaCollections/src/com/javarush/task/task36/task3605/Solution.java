package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
//        String fileName = "/Users/olehbiloholovka/Desktop/fileJavaRushTask2";
        Set<Character> characters = new TreeSet<>();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            while (fis.available() > 0){
//                Character aChar = (char) fis.read();
//                aChar = Character.toLowerCase(aChar);
                char aChar = (char) Character.toLowerCase(fis.read());
                if (Character.isLetter(aChar)) {
                    characters.add(aChar);
                }
//                characters.add(aChar);
            }
        }
//        int iterator = 0;
        Iterator<Character> iterator = characters.iterator();
//
////        for (Character character : characters) {
////            System.out.print(character);
//////            iterator++;
////            iterator.
////            if (iterator > 4){
////                break;
////            }
////        }
        int iter = 0;
        while (iterator.hasNext() && iter++ < 5){
            System.out.print(iterator.next());
        }

//        Iterator<Character> iterator = characters.iterator();
//        int n = characters.size() < 5 ? characters.size() : 5;
//
//        for (int i = 0; i < n; i++) {
//            System.out.print((iterator.next()));
//        }

    }
}
