package com.wwdy.front.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/6 13:48
 */
@Data
public class ShopSearch {

    /**
     * 商品标题
     */
    private String title;

    /**
     * 起始价格
     */
    private Double minPrice;

    /**
     * 结束价格
     */
    private Double maxPrice;

    /**
     * 商品分类id
     */
    private Integer category;

    /**
     * 商品品牌id
     */
    private List<Integer> spuIds;

    /**
     * 当前页
     */
    private Long page;

    /**
     * 每页显示的条数
     */
    private Integer size;

    /**
     * 偏移量
     */
    private Long offset;

    /**
     * 用户id
     */
    private Integer userId;

}
