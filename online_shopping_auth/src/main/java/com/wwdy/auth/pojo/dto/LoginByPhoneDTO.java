package com.wwdy.auth.pojo.dto;

import lombok.Data;

/**
 * @author wwdy
 * @date 2022/3/9 9:58
 */
@Data
public class LoginByPhoneDTO {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;
}
