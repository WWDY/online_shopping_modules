package com.wwdy.front.feign.pojo.dto;

import lombok.Data;

/**
 * @author wwdy
 * @date 2022/2/21 15:28
 */
@Data
public class User {

    private Integer id;

    /**
     * 用户名（账号）
     */
    private String username;


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

}
