package com.wwdy.admin.feign.resource;

import com.wwdy.admin.feign.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/3/20 12:54
 */
public interface UserResource {

    /**
     * 通过token获取用户信息
     * @param token token
     * @return ResultVO<User>
     */
    @RequestMapping(value = "/api/login/info",method = RequestMethod.GET)
    ResultVO<User> getUser(@RequestParam("token") String token);

}
