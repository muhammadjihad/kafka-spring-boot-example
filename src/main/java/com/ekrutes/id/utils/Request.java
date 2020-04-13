package com.ekrutes.id.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request {

    private String endpoint;

    public Request(String endpoint){
        this.endpoint=endpoint;
    }

    private URL setUrl() throws MalformedURLException{
        URL url=new URL(this.endpoint);
        return url;
    }

    public String request() throws IOException{
        HttpURLConnection conn=(HttpURLConnection) setUrl().openConnection();
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    conn.getInputStream(),"UTF-8"
                )
            );

            String inputLine;
            StringBuffer resp = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                resp.append(inputLine);
            }

            return resp.toString();

        } else {
            return "nothing";
        }
    }

}