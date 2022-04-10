package com.wwdy.front.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwdy.front.pojo.vo.OrderVO;
import com.wwdy.front.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/10 13:17
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;


    /**
     * 获取用户订单
     * @return ResultVO<List<OrderVO>>
     */
    @GetMapping("/")
    public ResultVO<List<OrderVO>> getOrderVO(){
        List<OrderVO> userOrder = orderService.getUserOrder();
        return ResultUtil.success(userOrder);
    }

    /**
     * 分页查找订单
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<OrderVO>>
     */
    @GetMapping("/page")
    public ResultVO<PageVO<OrderVO>> getOrderPage(@Valid com.wwdy.front.feign.pojo.dto.Page pageDTO){
        Page<OrderVO> noticePage = orderService.selectOrderPage(pageDTO);
        PageVO<OrderVO> pageVO = PageVO.of(noticePage.getRecords(), pageDTO.getPage(), pageDTO.getSize(), noticePage.getTotal());
        return ResultUtil.success(pageVO);
    }

    /**
     * 删除订单
     * @param id 订单id
     * @return ResultVO<String>
     */
    @DeleteMapping("/{id}")
    public ResultVO<String> deleteOrder(@PathVariable("id") String id){
        boolean res = orderService.deleteOrderById(id);
        if (res) {
            return ResultUtil.success("","删除成功");
        }
        return ResultUtil.error("删除失败");
    }
}
