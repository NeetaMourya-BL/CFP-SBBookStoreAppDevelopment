package com.bridgelabz.bookstoreapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
@Slf4j
public class BookStoreAppApplication {
    public static void main(String[] args) {
        log.info("Welcome to Book Store App");
        ApplicationContext context = SpringApplication.run(BookStoreAppApplication.class, args);
        log.info("Book Store App Started in {} Environment",
                context.getEnvironment().getProperty("environment"));
        log.info("Book Store App DB User is {}",
                context.getEnvironment().getProperty("spring.datasource.username"));
    }
}