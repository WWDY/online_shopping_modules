package com.wwdy.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.wwdy.auth.pojo.dto.LoginDTO;
import com.wwdy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wwdy
 * @date 2022/2/23 17:20
 */
@Controller
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
     * 登录
     * @param loginDTO 登录信息
     * @return callBackUrl
     */
    @PostMapping("/")
    public String login(@RequestBody LoginDTO loginDTO,
                        HttpServletResponse response,
                        @RequestParam("callBack")String callBack){
        loginDTO.setResponse(response);
        String token = userService.login(loginDTO);
        if (StrUtil.isNotEmpty(token)) {
            return StrUtil.format("redirect:{}?token={}",callBack,token);
        }
        return StrUtil.format("redirect:{}",loginUrl);
    }

    /**
     * sso单点登录
     * @return callBackUrl
     */
    @GetMapping("/sso")
    public String sso(@RequestParam("callBack")String callBack) {
        String token = userService.sso();
        if (StrUtil.isNotEmpty(token)) {
            return StrUtil.format("redirect:{}?token={}",callBack,token);
        }
        return StrUtil.format("redirect:{}",loginUrl);
    }

    /**
     * 注销登录
     * @return loginPage
     */
    @GetMapping("/logout")
    public String sso(){
        userService.logout();
        return StrUtil.format("redirect:{}",loginUrl);
    }

}
