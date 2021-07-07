package com.example.android_layout_animations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class NetworkAdapter {

    public static final int CONNECT_TIMEOUT = 3000;
    public static final int READ_TIMEOUT = 3000;

    static String httpRequest(String urlString) {
        return httpRequest(urlString, null);
    }

    static String httpRequest(String urlString, Map<String, String> headerProperties) {
        // add objects for later use
        String result = "";
        InputStream inputStream = null;
        HttpURLConnection connection = null;

        try {
            // convert our string url into a url object
            URL url = new URL(urlString);
            // create our connection object
            connection = (HttpURLConnection) url.openConnection();
            // set timeout
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            // optional to support apis with header requirements
            // S02M02-9 add support for header properties
            if(headerProperties != null) {
                for(Map.Entry<String, String> entry: headerProperties.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // executes the connection
            connection.connect();

            // process the response
            final int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) { // checks if we have a good response
                inputStream = connection.getInputStream();
                if(inputStream != null) {
                    // build reader object to read from the stream
                    InputStreamReader isReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isReader);
                    StringBuilder builder = new StringBuilder();

                    // reading from the stream into a string
                    String line = reader.readLine();
                    while(line != null) {
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
            result = "MalformedUrl";
        } catch (IOException e) {
            e.printStackTrace();
            result = "IOException";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // cleanup
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}