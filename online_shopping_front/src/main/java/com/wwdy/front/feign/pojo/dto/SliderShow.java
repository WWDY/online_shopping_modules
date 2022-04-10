package com.wwdy.front.feign.pojo.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/3/19 16:24
 */
@Data
public class SliderShow implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 轮播图名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 路由地址
     */
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

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}