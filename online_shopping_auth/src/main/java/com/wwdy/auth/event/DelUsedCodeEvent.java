package com.wwdy.auth.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * @author wwdy
 * @date 2022/3/12 12:32
 */
@Getter
public class DelUsedCodeEvent extends ApplicationEvent {

    private final DelUsedCodeEventData delUsedCodeEventData;

    public DelUsedCodeEvent(DelUsedCodeEventData delUsedCodeEventData) {
        super(delUsedCodeEventData);
        this.delUsedCodeEventData = delUsedCodeEventData;
    }

    @Data
    @Builder
    public static class DelUsedCodeEventData implements Serializable{
        /**
         * redis-key
         */
        private String keyPrefix;

        /**
         * 手机号码
         */
        private String phone;

        /**
         * 验证码
         */
        private String code;
    }
}
