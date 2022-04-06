package com.wwdy.front.feign.service;

import com.wwdy.front.feign.pojo.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/4/4 13:24
 */
public interface UserService {

    /**
     * 通过token获取用户信息
     * @param token token
     * @return ResultVO<UserDO>
     */
    @GetMapping("/api/login/info")
    ResultVO<User> getUserInfoByToken(@RequestParam("token") String token);
}
