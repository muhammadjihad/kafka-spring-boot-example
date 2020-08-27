package com.ekrutes.id.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.el.lang.FunctionMapperImpl.Function;

abstract class Request2{

    public Request2(String url){
        this.url=url;
    }

    private String url;

    private URL setTargetURL()throws MalformedURLException{
        URL url=new URL(this.url);
        return url;
    }

    public request(Function oke){

    }

}

public class Request {

    private String endpoint;

    public Request(String endpoint){
        this.endpoint=endpoint;
    }

    private URL setUrl() throws MalformedURLException{
        URL url=new URL(this.endpoint);
        return url;
    }

    public String request(String method,String data) throws IOException{
        HttpURLConnection conn=(HttpURLConnection) setUrl().openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");

        // for post only
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();
        
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