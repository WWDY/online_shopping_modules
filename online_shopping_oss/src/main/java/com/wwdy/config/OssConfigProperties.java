package com.wwdy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wwdy
 * @date 2022/3/14 11:36
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class OssConfig {
    /**
     * endpoint
     */
    private String endpoint;

    /**
     *  accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 填写Bucket名称
     */
    private String bucketName;

    /**
     * host
     */
    private String host;
}
