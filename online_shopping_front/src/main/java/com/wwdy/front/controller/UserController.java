package com.wwdy.front.controller;

import com.wwdy.front.feign.AuthClient;
import com.wwdy.front.feign.pojo.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/4/4 13:27
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthClient authClient;

    /**
     * 通过token获取用户信息
     * @param token token
     * @return ResultVO<User>
     */
    @GetMapping("/info")
    public ResultVO<User> getUserInfo(@RequestParam("token") String token) {
        return authClient.getUserInfoByToken(token);
    }
}
