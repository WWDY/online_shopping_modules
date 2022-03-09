package com.wwdy.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.wwdy.auth.enums.MessageResponseEnum;
import com.wwdy.auth.enums.RedisCodePrefixKeyEnum;
import com.wwdy.auth.pojo.dto.UserDTO;
import com.wwdy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.ResultVO;

import javax.lang.model.type.NullType;
import javax.validation.Valid;

/**
 * @author wwdy
 * @date 2022/2/21 16:10
 */
@RequestMapping("/api/register")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;


    /**
     * 用户注册
     * @param user 注册信息
     * @return ResultVO
     */
    @PostMapping("/")
    public ResultVO<NullType> register(@RequestBody @Valid UserDTO user){
        if (userService.register(user)) {
            return ResultUtil.success("注册成功");
        }
        return ResultUtil.error("注册失败");
    }

    /**
     * 获取短信验证码
     * @param phone 手机号
     * @return ResultVO<String>
     */
    @GetMapping("/code")
    public ResultVO<String> sendCode(@RequestParam("phone")String phone){
        String code = userService.sendCode(phone, RedisCodePrefixKeyEnum.REGISTER_CODE.getKey());
        if (StrUtil.isEmpty(code)) {
            return ResultUtil.error("获取验证码失败，请稍后再试");
        }else {
            if(StrUtil.equals(code,MessageResponseEnum.SUCCESS.getCode())){
                return ResultUtil.success();
            }
            return ResultUtil.error(Integer.valueOf(code),MessageResponseEnum.getMsg(code));
        }
    }

}
