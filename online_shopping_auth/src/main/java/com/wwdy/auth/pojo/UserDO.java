package com.wwdy.auth.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wwdy
 * @date 2022/2/21 15:28
 */
@Data
@TableName("user_info")
public class UserDO {
    @TableId(type = IdType.AUTO)
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}
