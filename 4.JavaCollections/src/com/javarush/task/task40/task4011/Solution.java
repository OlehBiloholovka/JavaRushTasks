package com.javarush.task.task40.task4011;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* 
Свойства URL
*/

public class Solution {
    private static final String NOT_VALID_PARAMATER = "Parameter %s is not a valid URL.";

    public static void main(String[] args) throws IOException {
        decodeURLString("https://www.amrood.com/index.htm?language=en#j2se");
    }

    public static void decodeURLString(String s) throws MalformedURLException {
        try {
            URL url = new URL(s);
            System.out.println(url.getProtocol());
            System.out.println(url.getAuthority());
            System.out.println(url.getFile());
            System.out.println(url.getHost());
            System.out.println(url.getPath());
            System.out.println(url.getPort());
            System.out.println(url.getDefaultPort());
            System.out.println(url.getQuery());
            System.out.println(url.getRef());
        } catch (MalformedURLException e) {
            System.out.println(String.format(NOT_VALID_PARAMATER, s));
//            e.printStackTrace();
        }
//        String protocol = url.getProtocol();
//        String authority = url.getAuthority();
//        String file = url.getFile();
//        String host = url.getHost();
//        String path = url.getPath();
//        int port = url.getPort();
//        int defaultPort = url.getDefaultPort();
//        String query = url.getQuery();
//        String ref = url.getRef();
//
//        System.out.println("- " + protocol);
//        System.out.println( authority);
//        if (file.isEmpty()) {
//            System.out.println(String.format(NOT_VALID_PARAMATER, "file"));
//        }else {
//            System.out.println(file);
//        }
//        System.out.println(host);
//        if (path.isEmpty()) {
//            System.out.println(String.format(NOT_VALID_PARAMATER, "path"));
//        } else {
//            System.out.println(path);
//        }
//        if (port == -1) {
//            System.out.println(String.format(NOT_VALID_PARAMATER, "port"));
//        }else {
//            System.out.println(port);
//        }
//        System.out.println(defaultPort);
//        if (query == null){
//            System.out.println(String.format(NOT_VALID_PARAMATER, "query"));
//        } else {
//            System.out.println(query);
//        }
//        if (ref == null) {
//            System.out.println(String.format(NOT_VALID_PARAMATER, "ref"));
//        } else {
//            System.out.println(ref);
//        }
    }
}

