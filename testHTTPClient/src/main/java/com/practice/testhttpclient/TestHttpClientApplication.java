package com.practice.testhttpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class TestHttpClientApplication {
    private static final Logger logger = LoggerFactory.getLogger(TestHttpClientApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(TestHttpClientApplication.class, args);
        logger.debug("--Application Started--");
    }
}