package com.example.layoutanimations;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class NetworkAdapter {

    static String httpRequest(String urlString){
        return httpRequest(urlString, null);
    }

    static String httpRequest(String urlString, Map<String, String> headerProperties) {
        String result = "";
        InputStream inputStream = null;
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();

            if(headerProperties!=null) {
                for(Map.Entry<String, String> entry : headerProperties.entrySet()){
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.connect();

            final int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                if (inputStream != null) {
                    InputStreamReader isReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isReader);
                    StringBuilder builder = new StringBuilder();

                    String line = reader.readLine();
                    while(line != null){
                        builder.append(line);
                        line = reader.readLine();
                    }
                    result = builder.toString();

                }
            } else {
                throw new IOException();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = "MALFORMED EXCEPTION";
        } catch (IOException e) {
            e.printStackTrace();
            result = "IOEXCEPTION";
        } finally {
            if(inputStream != null) {
                try{
                    inputStream.close();}
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }}
        return result;
    }
}