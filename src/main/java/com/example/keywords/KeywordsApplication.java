package com.example.keywords;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.keywords.dao")
public class KeywordsApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeywordsApplication.class, args);
    }
}
