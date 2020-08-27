package com.ekrutes.id;

import java.io.IOException;

import com.ekrutes.id.utils.Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.ekrutes.id.models.Service1;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    private final String service="/data-broker";
    private final String version="/v1";

    private final String endpoint=service+version;

    private final String serviceEndpointTarget="http://172.16.17.236:8090";
    
    @Autowired
    private KafkaTemplate<String,String>sender;

    // @RequestMapping(endpoint+"/")
    // public String index(){
    //     Request req = new Request("http://localhost:8085/data-broker/v1/kirim");
    //     try{
    //         return req.request("POST");
    //     } catch (IOException err){
    //         return err.toString();
    //     }
    // }

    @PostMapping(endpoint+"/kirim")
    public String sendMessage(@RequestBody JsonNode model){
        System.out.println("IT'S WORKED");
        // ListenableFuture oke = sender.send("ekrutes_notification_2",model.toString());
        sender.send("test", model.toString());
        // oke.addCallback("Berhasil", "GAGAL");
        return "Hello";
    }

    @KafkaListener(groupId = "orang1",topics="test")
    public void service1(String in){
        JSONObject obj = new JSONObject(in);
        try{
            Request req=new Request(serviceEndpointTarget+"/service-1");
            String resp=req.request("POST",in);
            System.out.println("SERVICE 1");
            System.out.println(resp);
            System.out.println("======================");
        }  catch (IOException io){
            System.out.println(io);
        }
        
    }

    @KafkaListener(groupId = "orang2",topics = "test")
    public void service2(String in){
        try{
            Request req=new Request(serviceEndpointTarget+"/service-2");
            String resp=req.request("POST", in);
            System.out.println("SERVICE 2");
            System.out.println(resp);
            System.out.println("======================");
        } catch (IOException io){
            System.out.println(io);
        }
    }

    // @KafkaListener(groupId="ekrutes_platform_analytics",topics="ekrutes_notification")
    // public void service3(String in){
    //     try{
    //         System.out.println("SERVICE 3 WORKED");
    //         Request req=new Request(serviceEndpointTarget+"/service-3");
    //         String resp=req.request("POST", in);
    //         System.out.println("SERVICE 3");
    //         System.out.println(resp);
    //         System.out.println("======================");
    //     } catch(IOException io){
    //         System.out.println(io);
    //     }
    // }

    // @RequestMapping(endpoint+"/demo")
    // public String kafkaDemo(){

    //     String bootstrapServer="0.0.0.0:9092";
    //     Properties props = new Properties();
    //     props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    //     props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,org.apache.kafka.common.serialization.StringSerializer
    // }

}