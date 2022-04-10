package com.wwdy.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwdy.auth.pojo.UserDO;
import com.wwdy.auth.pojo.dto.PageDTO;
import com.wwdy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;

/**
 * @author wwdy
 * @date 2022/4/10 14:48
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 分页查找用户
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<User>>
     */
    @GetMapping("/")
    public ResultVO<PageVO<UserDO>> getUserPage(@Valid PageDTO pageDTO){
        Page<UserDO> noticePage = userService.selectUserPage(pageDTO);
        PageVO<UserDO> pageVO = PageVO.of(noticePage.getRecords(), pageDTO.getPage(), pageDTO.getSize(), noticePage.getTotal());
        return ResultUtil.success(pageVO);
    }

    /**
     * 更新用户信息
     * @param userDO 用户信息
     * @return ResultVO<String>
     */
    @PutMapping("/")
    public ResultVO<String> updateUser(@RequestBody UserDO userDO){
        userDO.setUsername(null);
        userDO.setAddress(null);
        userDO.setName(null);
        if(StrUtil.isNotEmpty(userDO.getPassword())){
            userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        }else {
            userDO.setPassword(null);
        }
        boolean res = userService.updateById(userDO);
        if (res) {
            return ResultUtil.success("","更新成功");
        }
        return ResultUtil.error("更新失败");
    }
}
