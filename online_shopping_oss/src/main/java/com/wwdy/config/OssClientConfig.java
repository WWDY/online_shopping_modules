package com.wwdy.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wwdy
 * @date 2022/3/14 12:05
 */
@Configuration
@RequiredArgsConstructor
public class OssClientConfig {
    private final OssConfigProperties configProperties;

    /**
     * 创建ossClient
     * @return OSS
     */
    @Bean
    public OSS ossClient(){
        OSSClientBuilder clientBuilder = new OSSClientBuilder();
        return clientBuilder.build(configProperties.getEndpoint(), configProperties.getAccessKeyId(), configProperties.getAccessKeySecret());
    }

}
