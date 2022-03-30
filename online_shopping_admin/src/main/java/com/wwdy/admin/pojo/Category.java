package com.wwdy.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/3/25 13:00
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @NotEmpty(message = "标签名称不能为空")
    private String name;

    /**
     * 父标签
     */
    @NotNull(message = "父标签不能为空")
    private Integer parentId;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 删除字段
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}