package com.wwdy.auth.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author wwdy
 * @date 2022/2/23 17:24
 */
@Data
public class LoginDTO {

    /**
     * 账号
     */
    @NotEmpty(message = "账号不能为空")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}
