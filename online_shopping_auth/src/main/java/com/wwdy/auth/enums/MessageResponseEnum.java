package com.wwdy.auth.enums;

import lombok.Getter;

/**
 * @author wwdy
 * @date 2022/2/22 19:35
 */
@Getter
public enum MessageResponseEnum {
    /**
     * 发送消息返回状态
     */
    SUCCESS("0000","成功"),
    SYSTEM_ERROR("I005","系统异常"),
    SERVICE_ERROR("9999","服务异常"),
    BUSINESS_ERROR("1999","业务异常");
    private final String code;
    private final String msg;


    MessageResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
