package com.ekrutes.id.models;

import org.json.JSONObject;

public class Service1 {
    private int status;
    private String message;

    public int getStatus(){
        return this.status;
    }

    public String getMessage(){
        return this.message;
    }

    public String jsonData(){
        JSONObject obj=new JSONObject();
        obj.put("status", this.status);
        obj.put("message", this.message);
        return obj.toString();
    }
}