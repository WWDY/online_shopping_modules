package com.wwdy.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author  wwdy
 * @date  2022/2/16 11:05
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OnlineShoppingAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingAuthApplication.class, args);
    }

}
