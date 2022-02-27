package com.wwdy.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wwdy
 * @date 2022/2/23 11:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.message")
public class MessageConfigProperties {
    private String url;
    private String appcode;
    private String templateId;
    private String expireMinutes;
}
