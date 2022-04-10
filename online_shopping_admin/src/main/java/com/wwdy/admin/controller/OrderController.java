package com.wwdy.admin.controller;

import com.wwdy.admin.feign.FrontClient;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;

/**
 * @author wwdy
 * @date 2022/4/10 15:49
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final FrontClient frontClient;


    /**
     * 分页查找订单
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<OrderVO>>
     */
    @GetMapping("/")
    ResultVO<PageVO<OrderVO>> getOrderPage(@Valid PageDTO pageDTO) {
        return frontClient.getOrderPage(pageDTO);
    }

    /**
     * 删除订单
     * @param id 订单id
     * @return ResultVO<String>
     */
    @DeleteMapping("/{id}")
    ResultVO<String> deleteOrder(@PathVariable("id") String id) {
        return frontClient.deleteOrder(id);
    }
}
