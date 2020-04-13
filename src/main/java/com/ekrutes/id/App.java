package com.ekrutes.id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

/**
 * Hello world!
 *
 */

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class App
{

    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
