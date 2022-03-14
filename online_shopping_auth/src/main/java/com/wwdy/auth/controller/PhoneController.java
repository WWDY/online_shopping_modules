package com.wwdy.auth.controller;

import com.wwdy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import result.ResultUtil;
import result.enums.ResultEnum;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/3/11 17:10
 */
@RestController
@RequestMapping("/api/phone")
@RequiredArgsConstructor
public class PhoneController {

    private final UserService userService;

    /**
     * 检验手机号是否注册
     * @param phone 手机号码
     * @return ResultVO<String>
     */
    @GetMapping("/")
    public ResultVO<String> exists(@RequestParam String phone){
        boolean isExist = userService.phoneIsExist(phone);
        if (isExist) {
            return ResultUtil.error(ResultEnum.PHONE_IS_EXIST.getCode(),ResultEnum.PHONE_IS_EXIST.getMessage());
        }else {
            return ResultUtil.success();
        }
    }
}
