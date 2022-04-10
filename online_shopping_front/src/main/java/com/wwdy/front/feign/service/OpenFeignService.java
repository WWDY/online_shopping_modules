package com.wwdy.front.feign.service;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wwdy
 * @date 2022/4/4 13:31
 */
public interface OpenFeignService {

    /**
     * 签发openfeign token
     * @return String
     */
    @GetMapping("/api/openfeign/")
    String getOpenFeignToken();
}
