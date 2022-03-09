package com.wwdy.auth.enums;

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

    RedisCodePrefixKeyEnum(String key){
        this.key = key;
    }
}
