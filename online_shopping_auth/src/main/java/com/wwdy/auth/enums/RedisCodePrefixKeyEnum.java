package com.wwdy.auth.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * @author wwdy
 * @date 2022/3/9 10:14
 */
@Getter
public enum RedisCodePrefixKeyEnum {

    /**
     * 验证码类型Key
     */
    REGISTER_CODE("AUTH:CODE:REGISTER:"),
    LOGIN_CODE("AUTH:CODE:LOGIN:"),
    SEND_CODE_LIMIT_FREQUENCY("AUTH:CODE:SEND:");

    private final String key;

    /**
     * 获取枚举值
     * @param key 枚举key
     * @return String
     */
    public static String getLabel(String key){
        for (RedisCodePrefixKeyEnum value : values()) {
            if (StrUtil.equals(value.key,key)) {
                return value.name();
            }
        }
        return key;
    }

    RedisCodePrefixKeyEnum(String key){
        this.key = key;
    }
}
