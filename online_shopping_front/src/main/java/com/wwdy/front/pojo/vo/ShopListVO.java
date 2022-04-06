package com.wwdy.front.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/4/3 16:27
 */
@Data
public class ShopListVO {

    private Integer id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 原价
     */
    private Double price;

    /**
     * 现价
     */
    private Double discountPrice;

    /**
     * 轮播图地址
     */
    private String sliderShows;

    /**
     * 轮播图地址VO
     */
    private List<String> sliderShow;

    /**
     * spu描述
     */
    private String description;

    /**
     * 是否加入购物车
     */
    private Boolean cartStatus;

    /**
     * 是否收藏
     */
    private Boolean wishListStatus;
}
