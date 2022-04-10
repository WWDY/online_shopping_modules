package com.wwdy.front.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwdy.front.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author  wwdy
 * @date  2022/4/5 10:36
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 查找商品是否收加入购物车
     * @param cart 商品购物车信息
     * @return int
     */
    int selectExistCart(Cart cart);

    /**
     * 更新已经存在的购物车信息
     * @param cart 购物车信息
     * @return int
     */
    int updateExistCart(Cart cart);
}




