package com.wwdy.front.service;

import com.wwdy.front.pojo.dto.ShopPayInfo;

import java.util.Map;

/**
 * @author wwdy
 * @date 2022/4/8 20:48
 */
public interface AlipayService {

    /**
     * 创建订单
     * @param shopPayInfos 商品信息
     * @return String
     */
    String pay(ShopPayInfo shopPayInfos);

    /**
     * 校验订单支付状态
     * @param params 回调参数
     * @return boolean
     */
    boolean checkPay(Map<String,String> params);
}
