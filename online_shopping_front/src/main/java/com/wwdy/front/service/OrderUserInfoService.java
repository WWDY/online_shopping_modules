package com.wwdy.front.service;

import com.wwdy.front.pojo.OrderUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author  wwdy
 * @date  2022/4/9 14:28
 */
public interface OrderUserInfoService extends IService<OrderUserInfo> {

    /**
     * 添加订单用户信息
     * @param order 用户信息
     * @return int
     */
    int insertOrderUserInfo(OrderUserInfo order);
}
