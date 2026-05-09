package com.chirayu.flowgrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class PawStackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PawStackApplication.class, args);
    }

}
