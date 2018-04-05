package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String [] getTokens(String query, String delimiter) {

        List<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        while (stringTokenizer.hasMoreTokens()){
//            String token = stringTokenizer.nextToken();
            list.add(stringTokenizer.nextToken());
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
//        return null;
    }
}
