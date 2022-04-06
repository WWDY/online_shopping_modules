package com.wwdy.front.controller;

import com.wwdy.front.feign.pojo.dto.Page;
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

    @GetMapping("/{id}")
    public ResultVO<ShopDetailVO> getShopById(@PathVariable("id") Integer id){
        ShopDetailVO shop = shopService.getShopDetailById(id);
        return ResultUtil.success(shop);
    }
}
