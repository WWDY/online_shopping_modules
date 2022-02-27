package com.wwdy.auth;

import com.wwdy.auth.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineShoppingAuthApplicationTests {

    @Autowired
    MessageService messageService;

    @Test
    void contextLoads() {

    }

}
