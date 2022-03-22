package com.wwdy;

import com.wwdy.admin.OnlineShoppingAdminApplication;
import com.wwdy.admin.feign.UserClient;
import com.wwdy.admin.feign.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import result.vo.ResultVO;

import javax.annotation.Resource;

@SpringBootTest(classes = OnlineShoppingAdminApplication.class)
class OnlineShoppingAdminApplicationTests {

    @Resource
    UserClient userClient;

    @Test
    void contextLoads() {
        ResultVO<User> user = userClient.getUser("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDc2NTY1NjMsImp0aSI6IjIiLCJzdWIiOiJsb2dpbiIsImlhdCI6MTY0NzU3MDE2M30.0km69W5nPXuuahC7xO9hHx75TzaNTzZGXHoD280MJPg");
        System.out.println(user);
    }

}
