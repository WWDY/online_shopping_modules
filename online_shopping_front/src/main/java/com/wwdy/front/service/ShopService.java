package com.wwdy.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.front.feign.pojo.dto.Page;
import com.wwdy.front.feign.pojo.dto.Shop;
import com.wwdy.front.pojo.vo.ShopDetailVO;
import com.wwdy.front.pojo.vo.ShopListVO;
import org.apache.ibatis.annotations.Param;
import result.vo.PageVO;

/**
 * @author wwdy
 * @date 2022/4/3 16:26
 */
public interface ShopService extends IService<Shop>{

    /**
     * 查询商品列表
     * @param page 分页对象
     * @return PageVO<ShopListVO>
     */
    PageVO<ShopListVO> getShopList(Page page);

    /**
     * 通过id获取商品详情
     * @param id id
     * @return ShopDetailVO
     */
    ShopDetailVO getShopDetailById(@Param("id") Integer id);
}
