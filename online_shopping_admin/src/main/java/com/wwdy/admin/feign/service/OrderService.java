package com.wwdy.admin.feign.service;

import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.vo.OrderVO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import result.vo.PageVO;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/4/10 15:43
 */
public interface OrderService {

    /**
     * 分页查找订单
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<OrderVO>>
     */
    @GetMapping("/api/order/page")
    ResultVO<PageVO<OrderVO>> getOrderPage(@SpringQueryMap  PageDTO pageDTO);

    /**
     * 删除订单
     * @param id 订单id
     * @return ResultVO<String>
     */
    @DeleteMapping("/api/order/{id}")
    ResultVO<String> deleteOrder(@PathVariable("id") String id);
}
