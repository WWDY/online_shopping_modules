package com.wwdy.oss.pojo.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author wwdy
 * @date 2022/3/14 12:13
 */
@Data
@Builder
public class OssDTO {

    /**
     * accessId
     */
    private String accessId;

    /**
     * policy
     */
    private String policy;

    /**
     * signature
     */
    private String signature;

    /**
     * uploadDir
     */
    private String dir;

    /**
     * oss-host
     */
    private String host;

    /**
     * expire time
     */
    private String expire;
}
