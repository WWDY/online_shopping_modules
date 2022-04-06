package com.wwdy.front.controller;

import com.wwdy.front.pojo.Cart;
import com.wwdy.front.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.ResultVO;

import javax.validation.Valid;

/**
 * @author wwdy
 * @date 2022/4/5 11:18
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;


    /**
     * 添加购物车
     * @param cart 购物车
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> addCart(@RequestBody @Valid Cart cart) {
        int res = cartService.addCart(cart);
        if (res > 0) {
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error("添加失败");
    }

    /**
     * 移除购物车
     * @param cart 购物车
     * @return ResultVO<String>
     */
    @DeleteMapping("/")
    public ResultVO<String> removeCart(@RequestBody @Valid Cart cart) {
        int res = cartService.removeCart(cart.getShopId());
        if (res > 0) {
            return ResultUtil.success("移除成功");
        }
        return ResultUtil.error("移除失败");
    }
}
