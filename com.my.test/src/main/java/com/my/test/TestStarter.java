package com.my.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.my.test")

public class TestStarter {


    public static void main(String[] args) {
        SpringApplication.run(TestStarter.class);
    }
}
