package com.vodafone.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TaskApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(TaskApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
