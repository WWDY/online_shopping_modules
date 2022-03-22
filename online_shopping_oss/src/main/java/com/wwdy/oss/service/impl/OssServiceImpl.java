package com.wwdy.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.wwdy.oss.config.OssConfigProperties;
import com.wwdy.oss.pojo.dto.OssDTO;
import com.wwdy.oss.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wwdy
 * @date 2022/3/14 12:02
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements OssService {

    private final OSS ossClient;
    private final OssConfigProperties properties;

    @Override
    public OssDTO signature() {
        long expireTime = properties.getExpireTime();
        long expireEndTime = System.currentTimeMillis() + expireTime;
        String dir = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "/";
        Date expiration = new Date(expireEndTime);
        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        PolicyConditions policyConditions = new PolicyConditions();
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        return OssDTO.builder()
                .signature(postSignature)
                .accessId(properties.getAccessKeyId())
                .policy(encodedPolicy)
                .dir(dir)
                .expire(String.valueOf(expireEndTime / 1000))
                .host(properties.getHost()).build();
    }

    /**
     * 删除文件
     * @param fileName 文件完整路径
     */
    @Override
    public void delete(String fileName) {
       ossClient.deleteObject(properties.getBucketName(), fileName);
    }
}
