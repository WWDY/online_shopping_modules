package com.wwdy.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.front.feign.AuthClient;
import com.wwdy.front.feign.pojo.dto.User;
import com.wwdy.front.pojo.Cart;
import com.wwdy.front.service.CartService;
import com.wwdy.front.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author  wwdy
 * @date  2022/4/5 10:37
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService{

    private final AuthClient authClient;

    /**
     * 添加商品到购物车
     * @param cart 商品信息
     * @return int
     */
    @Override
    public int addCart(Cart cart) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = RequestUtil.getRequestToken(request);
        User user = authClient.getUserInfoByToken(token).getData();
        cart.setUserId(user.getId());
        cart.setStatus(true);
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>().eq("shop_id", cart.getShopId()).eq("user_id", user.getId());
        if(baseMapper.selectOne(queryWrapper) != null){
            return 1;
        }
        return baseMapper.insert(cart);
    }

    /**
     * 移除购物车中的商品
     * @param shopId 商品id
     * @return int
     */
    @Override
    public int removeCart(int shopId) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = RequestUtil.getRequestToken(request);
        User user = authClient.getUserInfoByToken(token).getData();
        return baseMapper.delete(new QueryWrapper<Cart>().eq("shop_id", shopId).eq("user_id", user.getId()));
    }
}




