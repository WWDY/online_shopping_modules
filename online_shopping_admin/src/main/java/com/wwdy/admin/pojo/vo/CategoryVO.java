package com.wwdy.admin.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/3/25 13:35
 */
@Data
public class CategoryVO {

    @JsonProperty(value = "key")
    private Integer id;

    /**
     * 名称
     */
    @JsonProperty(value = "title")
    private String name;


    private Integer value;

    /**
     * 父标签id
     */
    @JsonIgnore
    private Integer parentId;

    private List<CategoryVO> children;
}
