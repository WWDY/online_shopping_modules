package com.wwdy.front.mapper;

import com.wwdy.front.pojo.WishList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author  wwdy
 * @date  2022/4/5 10:37
 */
@Mapper
public interface WishListMapper extends BaseMapper<WishList> {

    /**
     * 查找商品是否收藏过
     * @param wishList 商品收藏信息
     * @return int
     */
    int selectExistWishList(WishList wishList);

    /**
     * 更新已经存在的收藏信息
     * @param wishList 收藏信息
     * @return int
     */
    int updateExistWishList(WishList wishList);
}




