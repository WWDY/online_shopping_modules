package com.wwdy.front.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/4 10:41
 */
@Data
public class ShopDetailVO {

    private Integer id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 原价
     */
    private Double price;

    /**
     * 现价
     */
    private Double discountPrice;

    /**
     * 上架状态
     */
    private String status;

    /**
     * 轮播图地址
     */
    private String sliderShows;

    /**
     * 轮播图地址VO
     */
    private List<String> sliderShow;

    /**
     * 商品详情
     */
    private String shopDescription;

    /**
     * spu分类信息
     */
    private Integer spuId;

    /**
     * 销售量
     */
    private Integer sales;

    /**
     * spu 描述
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
