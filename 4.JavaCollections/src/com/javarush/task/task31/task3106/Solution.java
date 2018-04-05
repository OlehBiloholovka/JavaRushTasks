package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        String resultFileName = args[0];
        int filePartCount = args.length - 1;
        String[] fileNamePart = new String[filePartCount];
        for (int i = 0; i < filePartCount; i++) {
            fileNamePart[i] = args[i + 1];
        }
        Arrays.sort(fileNamePart);

        List<FileInputStream> fisList = new ArrayList<>();
        for (int i = 0; i < filePartCount; i++) {
            fisList.add(new FileInputStream(fileNamePart[i]));
        }
        SequenceInputStream seqInStream = new SequenceInputStream(Collections.enumeration(fisList));
        ZipInputStream zipInStream = new ZipInputStream(seqInStream);
        FileOutputStream fileOutStream = new FileOutputStream(resultFileName);
        byte[] buf = new byte[1024 * 1024];
        while (zipInStream.getNextEntry() != null) {
            int count;
            while ((count = zipInStream.read(buf)) != -1) {
                fileOutStream.write(buf, 0, count);
            }
        }
        seqInStream.close();
        zipInStream.close();
        fileOutStream.close();


//        String resultFileName = args[0];
//        String[] files = Arrays.copyOfRange(args, 1, args.length);
//        Arrays.sort(files);
//
//        Vector<InputStream> vector = new Vector<>();
//        for (int i = 1; i < files.length; i++) {
//            try(FileInputStream fileInputStream = new FileInputStream(files[i])) {
//                vector.add(fileInputStream);
//                fileInputStream.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        try(ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(vector.elements()));
//            FileOutputStream fileOutputStream = new FileOutputStream(resultFileName)){
//
//            zipInputStream.getNextEntry();
//            byte[] buffer = new byte[1024];
//            int count = 0;
//            while ((count = zipInputStream.read(buffer)) != -1){
//                fileOutputStream.write(buffer, 0, count);
//            }
//            zipInputStream.closeEntry();
//            zipInputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
