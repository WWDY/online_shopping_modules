package com.wwdy.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.wwdy.auth.enums.MessageResponseEnum;
import com.wwdy.auth.enums.RedisCodePrefixKeyEnum;
import com.wwdy.auth.pojo.dto.LoginByPhoneDTO;
import com.wwdy.auth.pojo.dto.LoginDTO;
import com.wwdy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wwdy
 * @date 2022/2/23 17:20
 */
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    /**
     * 登录页面地址
     */
    @Value("${page.login-url}")
    private String loginUrl;



    /**
     * 登录-账号密码登录
     * @param loginDTO 登录信息
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> login(@RequestBody LoginDTO loginDTO){
        String token = userService.login(loginDTO);
        if (StrUtil.isNotEmpty(token)) {
            return ResultUtil.success(token);
        }
        return ResultUtil.error("登录失败");
    }

    /**
     * 获取短信验证码
     * @param phone 手机号
     * @return ResultVO<String>
     */
    @GetMapping("/code")
    public ResultVO<String> sendCode(@RequestParam("phone")String phone){
        String code = userService.sendCode(phone, RedisCodePrefixKeyEnum.LOGIN_CODE.getKey());
        if (StrUtil.isEmpty(code)) {
            return ResultUtil.error("获取验证码失败，请稍后再试");
        }else {
            if(StrUtil.equals(code, MessageResponseEnum.SUCCESS.getCode())){
                return ResultUtil.success();
            }
            return ResultUtil.error(Integer.valueOf(code),MessageResponseEnum.getMsg(code));
        }
    }

    /**
     * 登录-验证码登录
     * @param login 登录信息
     * @return ResultVO<String>
     */
    @PostMapping("/phone")
    public ResultVO<String> login(@RequestBody LoginByPhoneDTO login) {
        String token = userService.login(login);
        if (StrUtil.isNotEmpty(token)) {
            return ResultUtil.success(token);
        }
        return ResultUtil.error("登录失败");
    }

    /**
     * sso单点登录
     * @return ResultVO<String>
     */
    @GetMapping("/sso")
    public ResultVO<String> sso(HttpServletResponse response) {
        String token = userService.sso();
        if (StrUtil.isNotEmpty(token)) {
            return ResultUtil.success(token);
        }
        response.addHeader("Location",loginUrl);
        return ResultUtil.error();
    }

    /**
     * 注销登录
     * @return ResultVO<String>
     */
    @GetMapping("/logout")
    public ResultVO<String> logout(HttpServletResponse response){
        userService.logout();
        response.addHeader("Location",loginUrl);
        return ResultUtil.success("注销成功");
    }

}
