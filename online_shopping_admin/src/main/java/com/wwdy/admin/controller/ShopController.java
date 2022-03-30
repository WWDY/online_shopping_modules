package com.wwdy.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwdy.admin.pojo.Shop;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.ShopUpdate;
import com.wwdy.admin.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;

/**
 * @author  wwdy
 * @date  2022/3/29 15:27
 */
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    /**
     * 添加商品
     * @param shop 商品信息
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> addShop(@Valid @RequestBody Shop shop){
        int res = shopService.addShop(shop);
        if (res > 0) {
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error("添加失败");
    }

    /**
     * 通过id查找商品
     *
     * @param id id
     * @return ResultVO<Shop>
     */
    @GetMapping("/{id}")
    public ResultVO<Shop> getShopById(@PathVariable("id") Integer id){
        Shop shop = shopService.selectShopById(id);
        return ResultUtil.success(shop);
    }

    /**
     * 分页查找商品
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<Shop>>
     */
    @GetMapping("/")
    public ResultVO<PageVO<Shop>> getShopPage(@Valid PageDTO pageDTO){
        Page<Shop> page = shopService.selectShopPage(pageDTO);
        PageVO<Shop> pageVO = PageVO.of(page.getRecords(), pageDTO.getPage(), pageDTO.getSize(), page.getTotal());
        return ResultUtil.success(pageVO);
    }

    /**
     * 更新商品
     * @param update 更新内容
     * @return ResultVO<String>
     */
    @PatchMapping("/")
    public ResultVO<String> updateShop(@Valid @RequestBody ShopUpdate update) {
        int res = shopService.updateShop(update);
        if (res > 0) {
            return ResultUtil.success("更新成功");
        }
        return ResultUtil.error("更新失败");
    }

    /**
     * 通过id删除商品
     * @param id id
     * @return ResultVO<String>
     */
    @DeleteMapping("/{id}")
    public ResultVO<String> delShop(@PathVariable("id") Integer id){
        int res = shopService.delShop(id);
        if (res > 0) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error("删除失败");
    }
}
