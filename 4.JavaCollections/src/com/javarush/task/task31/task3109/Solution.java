package com.javarush.task.task31.task3109;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        boolean isXML = fileName != null && fileName.endsWith(".xml");
        boolean isDirectory = Files.isDirectory(Paths.get(fileName));
        Properties properties = new Properties();



        try(FileInputStream fileInputStream = new FileInputStream(fileName)){

            if (isXML) {
                properties.loadFromXML(fileInputStream);
            } else if(!isDirectory){
                properties.load(fileInputStream);
            }
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }

        return properties;
    }


}
