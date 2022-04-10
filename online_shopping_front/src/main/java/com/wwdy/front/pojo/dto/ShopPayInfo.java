package com.wwdy.front.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/9 13:36
 */
@Data
public class ShopPayInfo {

    @NotEmpty
    private List<ShopInfo> shopPayInfoList;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    @Data
    public static class ShopInfo{
        @NotNull(message = "商品ID不能为空")
        private Integer id;

        @NotNull(message = "商品数量不能为空")
        private Integer count;
    }

}
