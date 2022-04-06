package com.wwdy.front.service;

import com.wwdy.front.pojo.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author  wwdy
 * @date  2022/4/5 10:37
 */
public interface CartService extends IService<Cart> {

    /**
     * 添加商品到购物车
     * @param cart 商品信息
     * @return int
     */
    int addCart(Cart cart);

    /**
     * 移除购物车中的商品
     * @param shopId 商品id
     * @return int
     */
    int removeCart (int shopId);
}
