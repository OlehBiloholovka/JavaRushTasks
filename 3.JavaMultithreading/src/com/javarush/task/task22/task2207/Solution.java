package com.javarush.task.task22.task2207;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        String fileName = null;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            fileName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            List<String> stringList = new ArrayList<>();

            bufferedReader.lines().forEach(s -> stringList.addAll(Arrays.asList(s.split(" "))));

            for (int i = 0; i < stringList.size(); i++) {
                for (int j = i+1; j < stringList.size(); j++) {
                    if (stringList.get(i).equals(new StringBuilder(stringList.get(j)).reverse().toString())){
                        Pair pair = new Pair();
                        pair.first = stringList.get(i);
                        pair.second = stringList.get(j);

                        if (!result.contains(pair)) result.add(pair);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.forEach(pair -> System.out.println(pair));
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
