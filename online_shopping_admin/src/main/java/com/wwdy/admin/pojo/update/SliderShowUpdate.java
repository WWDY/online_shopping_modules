package com.wwdy.admin.pojo.update;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wwdy
 * @date 2022/3/19 17:31
 */
@Data
public class SliderShowUpdate{
    /**
     * id
     */
    @NotNull(message = "id不能为空")
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
}
