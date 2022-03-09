package com.wwdy.auth.pojo.dto;

import lombok.Data;

/**
 * @author wwdy
 * @date 2022/2/22 20:19
 */
@Data
public class SendMessageDTO {

    /**
     * 验证码
     */
    private String code;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 验证码类型
     */
    private String key;
}
