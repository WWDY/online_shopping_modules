package com.wwdy.front.feign.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/3/22 10:41
 */
@Data
public class Spu implements Serializable {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属分类
     */
    private Integer category;

    /**
     * 描述
     */
    private String description;

    /**
     * 重量 g
     */
    private Integer weight;

    /**
     * 状态
     */
    private String status;

    /**
     * 逻辑删除字段
     */
    private Integer deleted;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    private static final long serialVersionUID = 1L;
}