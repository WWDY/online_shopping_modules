package com.wwdy.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.admin.pojo.Shop;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.ShopUpdate;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/29 14:43
 */
public interface ShopService extends IService<Shop> {
    /**
     * 添加商品
     * @param shop 商品信息
     * @return int
     */
    int addShop(Shop shop);

    /**
     * 通过id查找商品
     * @param id id
     * @return Shop
     */
    Shop selectShopById(int id);

    /**
     * 分页查找商品
     * @param pageDTO 查询信息
     * @return List<Shop>
     */
    Page<Shop> selectShopPage(PageDTO pageDTO);

    /**
     * 更新商品
     * @param update 更新信息
     * @return int
     */
    int updateShop(ShopUpdate update);

    /**
     * 通过id删除商品
     * @param id id
     * @return int
     */
    int delShop(int id);

    /**
     * 查询商品列表
     * @param ids id列表
     * @return List<Shop>
     */
    List<Shop> getShopListByIds(List<Integer> ids);

    /**
     * 批量更新商品
     * @param shopList 商品列表
     * @return boolean
     */
    boolean updateShopList(List<Shop> shopList);
}
