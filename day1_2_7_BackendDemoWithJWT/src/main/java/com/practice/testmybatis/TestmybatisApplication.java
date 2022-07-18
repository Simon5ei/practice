package com.practice.testmybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestmybatisApplication {
    private static final Logger logger = LoggerFactory.getLogger(TestmybatisApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(TestmybatisApplication.class, args);
        logger.debug("--Application Started--");
    }

}
