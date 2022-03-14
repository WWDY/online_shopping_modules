package com.wwdy.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wwdy
 * @date 2022/2/23 18:06
 */
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperties {
    /**
     * 盐值
     */
    private String jwtSecret;

    /**
     * 有效时长
     */
    private Long jwtExpirationInMs;
}
