package com.wwdy.front.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author wwdy
 * @date 2022/4/8 20:51
 */
@Data
public class AlipayRequest {

    /**
     * 商户订单号, 商户网站订单系统中唯一订单号, 必填
     */
    @JsonProperty("out_trade_no")
    String outTradeNo;

    /**
     * 付款金额, 必填 单位元
     */
    @JsonProperty("total_amount")
    Double totalAmount;

    /**
     * 订单名称, 必填
     */
    String subject;

    /**
     * 商品描述, 可空, 目前先用订单名称
     */
    String body;

    @JsonProperty("product_code")
    String productCode = "FAST_INSTANT_TRADE_PAY";

    @JsonProperty("timeout_express")
    String timeoutExpress = "15m";
}
