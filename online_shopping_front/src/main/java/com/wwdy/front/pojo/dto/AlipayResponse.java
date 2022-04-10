package com.wwdy.front.pojo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author wwdy
 * @date 2022/4/9 15:41
 */
@Data
public class AlipayResponse {

    private String charset;

    @JsonAlias("out_trade_no")
    private Long outTradeNo;

    private String method;

    @JsonAlias("total_amount")
    private Double totalAmount;

    private String sign;

    @JsonAlias("trade_no")
    private String tradeNo;

    @JsonAlias("auth_app_id")
    private String authAppId;

    private String version;

    @JsonAlias("app_id")
    private String appId;

    @JsonAlias("sign_type")
    private String signType;

    @JsonAlias("seller_id")
    private String sellerId;

    private String timestamp;
}
