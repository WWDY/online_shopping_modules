package com.wwdy.front.feign.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/3 11:11
 */
@Data
public class Shop implements Serializable {

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
