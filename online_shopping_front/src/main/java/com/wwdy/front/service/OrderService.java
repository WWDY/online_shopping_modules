package com.wwdy.front.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.front.pojo.Order;
import com.wwdy.front.pojo.vo.OrderVO;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/4/9 14:28
 */
public interface OrderService extends IService<Order> {

    /**
     * 添加订单
     * @param order 订单
     */
    void insertOrder(Order order);

    /**
     * 获取用户订单信息
     * @return List<OrderVO>
     */
    List<OrderVO> getUserOrder();

    /**
     * 分页查找订单
     *
     * @param page 查询信息
     * @return Page<Order>
     */
    Page<OrderVO> selectOrderPage(com.wwdy.front.feign.pojo.dto.Page page);

    /**
     * 通过订单号删除订单
     * @param orderId 订单号
     * @return boolean
     */
    boolean deleteOrderById(String orderId);
}
