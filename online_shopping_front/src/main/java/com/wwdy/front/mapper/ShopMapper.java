package com.wwdy.front.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwdy.front.feign.pojo.dto.Page;
import com.wwdy.front.feign.pojo.dto.Shop;
import com.wwdy.front.pojo.dto.ShopSearch;
import com.wwdy.front.pojo.vo.ShopDetailVO;
import com.wwdy.front.pojo.vo.ShopListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/4 10:23
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 分页查询商品列表
     * @param page 分页信息
     * @return List<ShopListVO>
     */
    List<ShopListVO> selectPageShopList(Page page);

    /**
     * 多条件分页查询商品列表
     * @param shopSearch 查询信息
     * @return List<ShopListVO>
     */
    List<ShopListVO> selectPageShopListByCondition(ShopSearch shopSearch);

    /**
     * 查询所有商品数量
     * @return long
     */
    long totalShopList();

    /**
     * 多条件查询所有符合条件商品数目
     * @param shopSearch 查询信息
     * @return long
     */
    long totalShopListByConditions(ShopSearch shopSearch);

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @param userId 用户id
     * @return ShopDetailVO
     */
    ShopDetailVO selectShopDetailById(@Param("id") Integer id,@Param("userId") Integer userId);

    /**
     * 获取用户收藏的商品
     * @param userId 用户id
     * @return List<ShopListVO>
     */
    List<ShopListVO> searchUserShopList(@Param("userId") Integer userId);

    /**
     * 获取用户购物车商品
     * @param userId 用户id
     * @return List<ShopListVO>
     */
    List<ShopListVO> searchUserCart(@Param("userId") Integer userId);
}
