package com.wwdy.auth;

import com.wwdy.auth.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class OnlineShoppingAuthApplicationTests {

    @Autowired
    MessageService messageService;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {}

}
