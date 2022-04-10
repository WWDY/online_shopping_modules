package com.wwdy.admin.feign.resource;

import com.wwdy.admin.feign.dto.User;
import com.wwdy.admin.pojo.dto.PageDTO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import result.vo.PageVO;
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

    /**
     * 分页查找用户
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<User>>
     */
    @GetMapping("/api/user/")
    ResultVO<PageVO<User>> getUserPage(@SpringQueryMap PageDTO pageDTO);

    /**
     * 更新用户信息
     * @param userDO 用户信息
     * @return ResultVO<String>
     */
    @PutMapping("/api/user/")
    ResultVO<String> updateUser(@RequestBody User userDO);

    /**
     * 签发openfeign token
     * @return String
     */
    @GetMapping("/api/openfeign/")
    String getOpenFeignToken();

}
