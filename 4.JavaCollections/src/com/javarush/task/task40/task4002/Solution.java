package com.javarush.task.task40.task4002;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("https://requestb.in/1ofcw4c1", "name=zapp&mood=good&locale=&id=778");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        HttpPost request = new HttpPost(url);
//        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", "Mozilla/5.0");



//        request.setEntity(new StringEntity(urlParameters,
//                ContentType.create("plain/text", Consts.UTF_8)));

        List<NameValuePair> valuePairs = new ArrayList<>();//
        String[] array = urlParameters.split("&");//
        for (String string : array) {//
            valuePairs.add(new BasicNameValuePair(string.substring(0, string.indexOf("=")), string.substring(string.indexOf("=") + 1)));
        }
        request.setEntity(new UrlEncodedFormEntity(valuePairs));//


        HttpResponse response = client.execute(request);



//        OutputStream outputStream = connection.getOutputStream();
//        BufferedWriter bufferedWriter = new BufferedWriter(
//                new OutputStreamWriter(outputStream, "UTF-8"));
//        bufferedWriter.write(urlParameters);
//        bufferedWriter.flush();
//        bufferedWriter.close();
//        outputStream.flush();
//        outputStream.close();


        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
