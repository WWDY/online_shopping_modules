package com.wwdy.front.service;

import com.wwdy.front.pojo.WishList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author  wwdy
 * @date  2022/4/5 10:37
 */
public interface WishListService extends IService<WishList> {

    /**
     * 添加收藏
     * @param wishList 收藏对象
     * @return int
     */
    int addWishList(WishList wishList);

    /**
     * 删除收藏
     * @param shopId 商品id
     * @return int
     */
    int removeWishList(int shopId);
}
