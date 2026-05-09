package com.chirayu.petmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author chirayu
 * @created  2026-05-01
 */

@SpringBootApplication
@EnableConfigServer
public class FlowGridConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowGridConfigServerApplication.class, args);
    }

}
