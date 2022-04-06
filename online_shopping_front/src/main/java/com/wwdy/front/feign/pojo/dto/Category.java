package com.wwdy.front.feign.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/3/25 13:35
 */
@Data
public class Category {

    private Integer key;

    /**
     * 名称
     */
    private String title;


    private Integer value;

    /**
     * 父标签id
     */
    @JsonIgnore
    private Integer parentId;

    private List<Category> children;
}
