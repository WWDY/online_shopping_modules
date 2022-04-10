package com.wwdy.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/4/8 21:46
 */
@TableName(value ="notice")
@Data
public class Notice implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 公告内容
     */
    @NotEmpty(message = "公告内容不能为空")
    private String content;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 逻辑删除字段
     */
    private Integer deleted;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}