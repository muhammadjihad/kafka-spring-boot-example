package com.ekrutes.id;

import java.io.IOException;

import com.ekrutes.id.utils.Request;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final String service="/data-broker";
    private final String version="/v1";

    private final String endpoint=service+version;
    
    @Autowired
    private KafkaTemplate<String,String>sender;

    @RequestMapping(endpoint+"/")
    public String index(){
        Request req = new Request("https://ekrutes.id:8001/akun-api/pilih-bahasa/");
        try{
            return req.request();
        } catch (IOException err){
            return err.toString();
        }
    }

    @PostMapping(endpoint+"/kirim")
    public String sendMessage(){

        JSONObject obj = new JSONObject();

        obj.put("status", 1);
        obj.put("message","Ekrutes Broker Service Works");

        sender.send("ekrutes_notification",obj.toString());
        return "Hello";
    }

    @KafkaListener(groupId ="ekrutes_db_elasticsearch",topics="ekrutes_notification")
    public void ekrutesNotificationConsumer(String in){
        JSONObject obj = new JSONObject(in);
        System.out.println(in);
        String message=obj.get("message").toString();
        System.out.println(message);
    }

    @KafkaListener(groupId = "ekrutes_db_mysql",topics = "ekrutes_notification")
    public void ekrutesNotificationConsumer2(String in){
        System.out.println("INI DARI YANG KEDUA");
    }

    // @RequestMapping(endpoint+"/demo")
    // public String kafkaDemo(){

    //     String bootstrapServer="0.0.0.0:9092";
    //     Properties props = new Properties();
    //     props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    //     props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,org.apache.kafka.common.serialization.StringSerializer
    // }

}