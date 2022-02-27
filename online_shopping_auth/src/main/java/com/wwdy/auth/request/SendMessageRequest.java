package com.wwdy.auth.request;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wwdy.auth.response.SendMessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wwdy
 * @date 2022/2/22 14:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SendMessageRequest extends RpcRequest<SendMessageResponse>{

    /**
     * 短信模板ID
     */
    @Alias("templateID")
    private String templateId = "0000000";

    /**
     * 发送频道
     * 0: 默认通道（默认值） 1: 高质量通道
     */
    private String channel = "1";

    /**
     * 发送字段
     */
    private String templateParamSet = "{},{}";

    /**
     * 验证码
     */
    @JsonIgnore
    private String code;

    /**
     * 短信有效时间
     */
    @JsonIgnore
    private String limitMinutes = "5";

    /**
     * 手机号码
     * 手机号码，采用 e.164 标准，格式为+[国家或地区码][手机号]
     */
    private String mobile;

    public void setMobile(String mobile) {
        this.mobile = "+86" + mobile;
    }



    @Override
    public TypeReference<SendMessageResponse> getResponseClass() {
        return new TypeReference<SendMessageResponse>() {};
    }
}
