package com.wwdy.admin.pojo.update;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wwdy
 * @date 2022/3/22 10:45
 */
@Data
public class SpuUpdate {

    @NotNull(message = "id不能为空")
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
    private Integer status;

}
