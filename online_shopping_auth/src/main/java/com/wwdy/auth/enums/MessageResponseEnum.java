package com.wwdy.auth.enums;

import cn.hutool.core.util.StrUtil;
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
    BUSINESS_ERROR("1999","业务异常"),
    SEND_FREQUENTLY("2999","发送频繁"),
    PHONE_ERROR("3999","手机号码格式错误"),
    PHONE_NOT_EXIST("4999","手机号码不存在");
    private final String code;
    private final String msg;


    MessageResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取反馈信息
     * @param code 返回代码
     * @return String
     */
    public static String getMsg(String code){
        for (MessageResponseEnum value : values()) {
            if(StrUtil.equals(value.code,code)){
                return value.msg;
            }
        }
        return null;
    }
}
