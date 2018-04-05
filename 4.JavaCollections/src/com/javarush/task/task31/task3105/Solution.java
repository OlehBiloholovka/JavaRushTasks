package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipPath = args[1];
        Path tempDir = Files.createTempDirectory("temp");




//        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
//            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath))){
//
//            while (zipInputStream.available() > 0){
//                ZipEntry zipEntry = zipInputStream.getNextEntry();
//                String entryName = zipEntry.getName();
//
////                Files.write(tempDir + entryName)
//
//            }
//        }

        Map<String, ByteArrayOutputStream> archivedFiles = new HashMap<>();
        getZipFiles(archivedFiles, zipPath);
        putZipFiles(archivedFiles,zipPath, fileName);


//        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath))) {
//            ZipEntry entry;
//            while ((entry = zipInputStream.getNextEntry()) != null) {
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[1024];
//                int count = 0;
//                while ((count = zipInputStream.read(buffer)) != -1)
//                    byteArrayOutputStream.write(buffer, 0, count);
//
//                archivedFiles.put(entry.getName(), byteArrayOutputStream);
//
//            }
//        }

//        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath)){
//
//        }
    }

    private static void getZipFiles(Map<String, ByteArrayOutputStream> archivedFiles, String zipPath){
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipInputStream.read(buffer)) != -1)
                    byteArrayOutputStream.write(buffer, 0, count);

                archivedFiles.put(entry.getName(), byteArrayOutputStream);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putZipFiles(Map<String, ByteArrayOutputStream> archivedFiles, String zipPath, String fileName){
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath))){
            archivedFiles.forEach((s, byteArrayOutputStream) -> {
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(s));
                    zipOutputStream.write(byteArrayOutputStream.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            zipOutputStream.putNextEntry(new ZipEntry("new" + Paths.get(fileName).getFileName()));
            Files.copy(Paths.get(fileName), zipOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
