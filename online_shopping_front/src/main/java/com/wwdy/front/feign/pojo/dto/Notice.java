package com.wwdy.front.feign.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/4/8 21:46
 */
@Data
public class Notice implements Serializable {

    private Integer id;

    /**
     * 公告内容
     */
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
    private LocalDateTime updatedTime;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    private static final long serialVersionUID = 1L;
}