package com.wwdy.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author  wwdy
 * @date  2022/3/14 15:30
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wwdy.admin.feign"})
public class OnlineShoppingAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingAdminApplication.class, args);
    }

}
