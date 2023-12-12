package com.example.spring20232;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Spring20232Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring20232Application.class, args);
    }

}
