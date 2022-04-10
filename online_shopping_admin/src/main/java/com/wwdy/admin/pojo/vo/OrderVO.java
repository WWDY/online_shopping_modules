package com.wwdy.admin.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/10 11:26
 */
@Data
public class OrderVO {

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单创建时间
     */
    private Long createdTime;

    /**
     * 支付状态
     */
    private Boolean payStatus;

    /**
     * 商品快照
     */
    private List<OrderSnapshot> snapshots;
}
