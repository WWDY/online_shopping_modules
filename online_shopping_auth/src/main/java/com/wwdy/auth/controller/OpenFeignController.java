package com.wwdy.auth.controller;

import com.wwdy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wwdy
 * @date 2022/4/3 13:27
 */
@RestController
@RequestMapping("/api/openfeign")
@RequiredArgsConstructor
public class OpenFeignController {

    private final UserService userService;


    /**
     * 签发openfeign token
     * @return String
     */
    @GetMapping("/")
    public String openFeignTokenSignature(){
        return userService.openFeignTokenSignature();
    }

}
