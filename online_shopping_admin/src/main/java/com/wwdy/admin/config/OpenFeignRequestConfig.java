package com.wwdy.admin.config;

import com.wwdy.admin.feign.UserClient;
import constant.JwtConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * @author wwdy
 * @date 2022/4/3 11:48
 */
@Configuration
@RequiredArgsConstructor
public class OpenFeignRequestConfig implements RequestInterceptor {

    private final UserClient authClient;

    /**
     * openfeign 调用添加认证信息
     * @param requestTemplate requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(JwtConstant.FEIGN_TOKEN_HEADER_NAME, authClient.getOpenFeignToken());
    }
}
