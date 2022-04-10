package com.wwdy.admin.controller;

import com.wwdy.admin.feign.UserClient;
import com.wwdy.admin.feign.dto.User;
import com.wwdy.admin.pojo.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;

/**
 * @author wwdy
 * @date 2022/4/10 14:53
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    /**
     * 分页查找用户
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<User>>
     */
    @GetMapping("/")
    public ResultVO<PageVO<User>> getNoticePage(@Valid PageDTO pageDTO){
       return userClient.getUserPage(pageDTO);
    }

    /**
     * 更新用户信息
     * @param userDO 用户信息
     * @return ResultVO<String>
     */
    @PutMapping("/")
    public ResultVO<String> updateUser(@RequestBody User userDO){
        return userClient.updateUser(userDO);
    }
}
