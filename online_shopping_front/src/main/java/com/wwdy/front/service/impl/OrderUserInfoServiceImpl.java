package com.wwdy.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.front.pojo.OrderUserInfo;
import com.wwdy.front.service.OrderUserInfoService;
import com.wwdy.front.mapper.OrderUserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author  wwdy
 * @date  2022/4/9 14:29
 */
@Service
public class OrderUserInfoServiceImpl extends ServiceImpl<OrderUserInfoMapper, OrderUserInfo> implements OrderUserInfoService{

    /**
     * 添加订单用户信息
     * @param order 用户信息
     * @return int
     */
    @Override
    public int insertOrderUserInfo(OrderUserInfo order) {
        return baseMapper.insert(order);
    }
}




