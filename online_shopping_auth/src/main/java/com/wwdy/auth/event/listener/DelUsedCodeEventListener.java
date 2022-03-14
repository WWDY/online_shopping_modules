package com.wwdy.auth.event.listener;

import com.wwdy.auth.enums.RedisCodePrefixKeyEnum;
import com.wwdy.auth.event.DelUsedCodeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author wwdy
 * @date 2022/3/12 12:33
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelUsedCodeEventListener {

    private final StringRedisTemplate stringRedisTemplate;

    @Async
    @EventListener
    public void delUsedCode(DelUsedCodeEvent.DelUsedCodeEventData delUsedCodeEventData){
        String key = delUsedCodeEventData.getKeyPrefix() + delUsedCodeEventData.getPhone();
        stringRedisTemplate.delete(key);
        log.info("验证码已使用,手机号={},类型={},验证码={}", delUsedCodeEventData.getPhone(), RedisCodePrefixKeyEnum.getLabel(delUsedCodeEventData.getKeyPrefix()), delUsedCodeEventData.getCode());
    }
}
