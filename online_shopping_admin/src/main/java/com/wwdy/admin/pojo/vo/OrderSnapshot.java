package com.wwdy.admin.pojo.vo;

import lombok.Data;

/**
 * @author  wwdy
 * @date  2022/4/9 14:27
 */
@Data
public class OrderSnapshot {


    private Integer id;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 商品id
     */
    private Integer shopId;

    /**
     * 商品标题
     */
    private String shopTitle;


    /**
     * 购买数量
     */
    private Integer shopCount;

    /**
     * 商品原价
     */
    private Double shopPrice;

    /**
     * 商品折扣价
     */
    private Double shopDiscountPrice;

    /**
     * 逻辑删除字段
     */
    private Integer deleted;

    /**
     * 更新时间
     */
    private Long updatedTime;

    /**
     * 创建时间
     */
    private Long createdTime;
}