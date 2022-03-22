package com.wwdy.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.wwdy.admin.annotation.valid.SpuStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/3/22 10:41
 */
@TableName(value ="spu")
@Data
public class Spu implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 所属分类
     */
    @NotNull(message = "id不能为空")
    private Integer category;

    /**
     * 描述
     */
    @NotEmpty(message = "描述不能为空")
    private String description;

    /**
     * 重量 g
     */
    @NotNull(message = "重量不能为空")
    private Integer weight;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @SpuStatus
    private Integer status;

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