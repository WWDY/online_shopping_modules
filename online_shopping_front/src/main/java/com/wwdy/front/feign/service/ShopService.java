package com.wwdy.front.feign.service;

import com.wwdy.front.feign.pojo.dto.Page;
import com.wwdy.front.feign.pojo.dto.Shop;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import result.vo.PageVO;
import result.vo.ResultVO;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/3 15:59
 */
public interface ShopService {
    /**
     * 分页查找商品
     * @param page 分页信息
     * @return ResultVO<PageVO<Shop>>
     */
    @GetMapping("/api/shop/")
    ResultVO<PageVO<Shop>> getShopPage(@SpringQueryMap Page page);

    /**
     * 通过id查找商品
     *
     * @param id id
     * @return ResultVO<Shop>
     */
    @GetMapping("/api/shop/{id}")
    ResultVO<Shop> getShopById(@PathVariable("id") Integer id);

    /**
     * 查询商品列表
     * @param ids id列表
     * @return ResultVO<List<Shop>>
     */
    @PostMapping("/api/shop/list")
    ResultVO<List<Shop>> getShopListByIds(@RequestBody List<Integer> ids);

    /**
     * 批量更新商品
     * @param shops 商品列表
     * @return ResultVO<String>
     */
    @PutMapping("/api/shop/list")
    ResultVO<String> updateShopList(@RequestBody List<Shop> shops);
}
