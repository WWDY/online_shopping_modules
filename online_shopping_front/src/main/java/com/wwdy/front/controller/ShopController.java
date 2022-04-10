package com.wwdy.front.controller;

import com.wwdy.front.feign.pojo.dto.Page;
import com.wwdy.front.pojo.dto.ShopSearch;
import com.wwdy.front.pojo.vo.ShopDetailVO;
import com.wwdy.front.pojo.vo.ShopListVO;
import com.wwdy.front.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ResultUtil;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/3 11:24
 */
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;


    /**
     * 分页查找商品
     * @param page 分页信息
     * @return ResultVO<PageVO<Shop>>
     */
    @GetMapping("/")
    public ResultVO<PageVO<ShopListVO>> getShopPage(@Valid Page page){
        PageVO<ShopListVO> shopList = shopService.getShopList(page);
        return ResultUtil.success(shopList);
    }

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return ResultVO<ShopDetailVO>
     */
    @GetMapping("/{id}")
    public ResultVO<ShopDetailVO> getShopById(@PathVariable("id") Integer id){
        ShopDetailVO shop = shopService.getShopDetailById(id);
        return ResultUtil.success(shop);
    }

    /**
     * 多条件查询商品
     * @param shopSearch 查询条件
     * @return ResultVO<PageVO<ShopListVO>>
     */
    @GetMapping("/search")
    public ResultVO<PageVO<ShopListVO>> getShopPageByConditions(ShopSearch shopSearch){
        PageVO<ShopListVO> listByConditions = shopService.getShopListByConditions(shopSearch);
        return ResultUtil.success(listByConditions);
    }

    /**
     * 获取用户收藏的商品
     * @return ResultVO<List<ShopListVO>>
     */
    @GetMapping("/wish-list")
    public ResultVO<List<ShopListVO>> searchWishList(){
        List<ShopListVO> userWishList = shopService.getUserWishList();
        return ResultUtil.success(userWishList);
    }

    /**
     * 获取用户购物车商品
     * @return ResultVO<List<ShopListVO>>
     */
    @GetMapping("/cart")
    public ResultVO<List<ShopListVO>> searchCart(){
        List<ShopListVO> userWishList = shopService.getUserCart();
        return ResultUtil.success(userWishList);
    }


}
