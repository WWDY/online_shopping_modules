package com.wwdy.admin.pojo.update;

import com.wwdy.admin.annotation.valid.SpuStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/29 14:43
 */
@Data
public class ShopUpdate implements Serializable {

    private Integer id;

    /**
     * 商品标题
     */
    @NotEmpty(message = "商品标题不能为空")
    private String title;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 原价
     */
    @NotNull(message = "原价不能为空")
    private Double price;

    /**
     * 现价
     */
    @NotNull(message = "现价不能为空")
    private Double discountPrice;

    /**
     * 上架状态
     */
    @NotNull(message = "上架状态不能为空")
    @SpuStatus
    private String status;

    /**
     * 轮播图地址
     */
    private List<String> sliderShow;

    /**
     * 商品详情
     */
    @NotEmpty(message = "商品详情不能为空")
    private String shopDescription;

    /**
     * spu分类信息
     */
    @NotNull(message = "spu分类信息不能为空")
    private Integer spuId;
}