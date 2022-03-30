package com.wwdy.admin.pojo.vo;

import lombok.Data;

/**
 * @author wwdy
 * @date 2022/3/26 17:16
 */
@Data
public class SpuVO {


    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属分类id
     */
    private Integer category;

    /**
     * 所属分类名称
     */
    private String categoryName;

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
}
