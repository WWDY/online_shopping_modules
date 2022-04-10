package com.wwdy.front.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author  wwdy
 * @date  2022/4/9 14:27
 */
@TableName(value ="order_snapshot")
@Data
@Builder
public class OrderSnapshot implements Serializable {

    @TableId(type = IdType.AUTO)
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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}