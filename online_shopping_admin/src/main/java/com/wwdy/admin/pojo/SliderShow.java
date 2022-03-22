package com.wwdy.admin.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/3/19 16:24
 */
@TableName(value ="slider_show")
@Data
public class SliderShow implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 轮播图名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 图片地址
     */
    @NotEmpty(message = "图片不能为空")
    private String url;

    /**
     * 路由地址
     */
    @NotEmpty(message = "路由地址不能为空")
    private String route;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableLogic
    private Integer deleted;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}