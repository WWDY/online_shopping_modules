package com.wwdy.admin.feign.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wwdy
 * @date 2022/3/20 12:56
 */
@Data
public class User {
    private Integer id;

    /**
     * 用户名（账号）
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否拥有管理员角色
     */
    private Boolean adminRole;

    /**
     * 姓名
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
