package com.wwdy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author  wwdy
 * @date  2022/2/23 15:52
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OnlineShoppingGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingGatewayApplication.class, args);
    }

}
