package com.wwdy.admin.service.impl;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.admin.converter.ShopConverter;
import com.wwdy.admin.exception.NotFoundRecordException;
import com.wwdy.admin.mapper.ShopMapper;
import com.wwdy.admin.pojo.Shop;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.ShopUpdate;
import com.wwdy.admin.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author  wwdy
 * @date  2022/3/29 14:43
 */
@Service
@RequiredArgsConstructor
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService{

    private final ShopConverter shopConverter;

    /**
     * 添加商品
     * @param shop 商品信息
     * @return int
     */
    @Override
    public int addShop(Shop shop) {
        String sliderShows = String.join(",", shop.getSliderShow());
        shop.setSliderShows(sliderShows);
        shop.setShopDescription(HtmlUtil.escape(shop.getShopDescription()));
        return baseMapper.insert(shop);
    }

    /**
     * 通过id查找商品
     * @param id id
     * @return Shop
     */
    @Override
    public Shop selectShopById(int id) {
        Shop shop = baseMapper.selectById(id);
        if(Optional.ofNullable(shop).isPresent()){
            shop.setShopDescription(HtmlUtil.unescape(shop.getShopDescription()));
            shop.setSliderShow(Arrays.asList(shop.getSliderShows().split(",")));
            return shop;
        }
        throw new NotFoundRecordException("商品不存在");
    }

    /**
     * 分页查找商品
     * @param pageDTO 查询信息
     * @return List<Shop>
     */
    @Override
    public Page<Shop> selectShopPage(PageDTO pageDTO) {
        Page<Shop> shopPage = new Page<>();
        shopPage.setSize(pageDTO.getSize());
        shopPage.setCurrent(pageDTO.getPage());
        Page<Shop> selectPage = baseMapper.selectPage(shopPage, null);
        List<Shop> res = selectPage.getRecords()
                .stream()
                .peek(shop -> {
                    shop.setShopDescription(HtmlUtil.unescape(shop.getShopDescription()));
                    shop.setSliderShow(Arrays.asList(shop.getSliderShows().split(",")));
                })
                .collect(Collectors.toList());
        return selectPage.setRecords(res);
    }

    /**
     * 更新商品
     * @param update 更新信息
     * @return int
     */
    @Override
    public int updateShop(ShopUpdate update) {
        Shop convert = shopConverter.convert(update);
        assert convert != null;
        convert.setSliderShows(String.join(",", convert.getSliderShow()));
        convert.setShopDescription(HtmlUtil.escape(convert.getShopDescription()));
        return baseMapper.updateById(convert);
    }

    /**
     * 通过id删除商品
     * @param id id
     * @return int
     */
    @Override
    public int delShop(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 查询商品列表
     * @param ids id列表
     * @return List<Shop>
     */
    @Override
    public List<Shop> getShopListByIds(List<Integer> ids) {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 批量更新商品
     * @param shopList 商品列表
     * @return boolean
     */
    @Override
    public boolean updateShopList(List<Shop> shopList) {
        return updateBatchById(shopList);
    }
}




