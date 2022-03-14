package com.wwdy;

import com.wwdy.service.OssService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineShoppingOssApplicationTests {

    @Autowired
    OssService ossService;

    @Test
    void contextLoads() {
        System.out.println(ossService.signature());
    }

}
