package com.wwdy.front.controller;

import com.wwdy.front.pojo.WishList;
import com.wwdy.front.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.ResultVO;

import javax.validation.Valid;

/**
 * @author wwdy
 * @date 2022/4/5 11:18
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListService wishListService;


    /**
     * 添加收藏
     * @param wishList 收藏信息
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> addWishList(@RequestBody @Valid WishList wishList) {
        int res = wishListService.addWishList(wishList);
        if (res > 0) {
            return ResultUtil.success("收藏成功");
        }
        return ResultUtil.error("收藏失败");
    }

    /**
     * 取消收藏
     * @param wishList 收藏信息
     * @return ResultVO<String>
     */
    @DeleteMapping("/")
    public ResultVO<String> removeWishList(@RequestBody @Valid WishList wishList) {
        Integer shopId = wishList.getShopId();
        int res = wishListService.removeWishList(shopId);
        if (res > 0) {
            return ResultUtil.success("取消收藏成功");
        }
        return ResultUtil.error("取消收藏失败");
    }

}
