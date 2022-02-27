package com.wwdy.auth.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author wwdy
 * @date 2022/2/24 17:39
 */
@Data
public class UserDTO {

    /**
     * 用户名（账号）
     */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4,max = 16,message = "用户名不合法")
    private String username;

    /**
     * 用户密码
     */
    @Length(min = 8,max = 16)
    @Pattern(regexp = "^\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*)(?=\\S*[a-z])(?=\\S*[.!@#$%^&*? ])\\S*$", message = "密码强度不合法")
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;

    private String address;

    @Pattern(regexp = "^(?:(?:\\+|00)86)?1(?:3[\\d]|4[5-7|9]|5[0-3|5-9]|6[5-7]|7[0-8]|8[\\d]|9[1|89])\\d{8}$",message = "手机号码不合法")
    private String phone;

    /**
     * 验证码
     */
    @NotEmpty(message = "验证码不能为空")
    private String code;
}
