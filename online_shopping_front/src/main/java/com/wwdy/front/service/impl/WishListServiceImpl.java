package com.wwdy.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.front.feign.AuthClient;
import com.wwdy.front.feign.pojo.dto.User;
import com.wwdy.front.mapper.WishListMapper;
import com.wwdy.front.pojo.WishList;
import com.wwdy.front.service.WishListService;
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
public class WishListServiceImpl extends ServiceImpl<WishListMapper, WishList> implements WishListService{

    private final AuthClient authClient;


    /**
     * 添加收藏
     * @param wishList 收藏对象
     * @return int
     */
    @Override
    public int addWishList(WishList wishList) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = RequestUtil.getRequestToken(request);
        User user = authClient.getUserInfoByToken(token).getData();
        wishList.setUserId(user.getId());
        wishList.setStatus(true);
        if(baseMapper.selectExistWishList(wishList) > 0){
            return baseMapper.updateExistWishList(wishList);
        }
        return baseMapper.insert(wishList);
    }

    /**
     * 删除收藏
     * @param shopId 商品id
     * @return int
     */
    @Override
    public int removeWishList(int shopId) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = RequestUtil.getRequestToken(request);
        User user = authClient.getUserInfoByToken(token).getData();
        QueryWrapper<WishList> queryWrapper = new QueryWrapper<WishList>()
                .eq("shop_id", shopId)
                .eq("user_id", user.getId());
        return baseMapper.delete(queryWrapper);
    }
}




