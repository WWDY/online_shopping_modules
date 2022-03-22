package com.wwdy.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wwdy
 * @date 2022/3/19 17:10
 */
@Data
public class PageDTO {

    /**
     * 查询页
     */
    @NotNull
    private Long page;

    /**
     * 页面大小
     */
    @NotNull
    private Integer size;
}
