package com.wwdy.admin.enums;

import lombok.Getter;

/**
 * @author wwdy
 * @date 2022/3/22 10:49
 */
@Getter
public enum SpuEnum {
    /**
     * 上架枚举
     */
    SHELVES("SHELVES","上架"),
    NO_SHELVES("NO_SHELVES","下架")
    ;


    private final String status;
    private final String label;

    SpuEnum(String status, String label) {
        this.status = status;
        this.label = label;
    }

    /**
     * 获取label
     * @param status 枚举值
     * @return String
     */
    public static String nameOf(String status){
        for (SpuEnum value : values()) {
            if(value.status.equals(status)){
                return value.label;
            }
        }
        return null;
    }
}
