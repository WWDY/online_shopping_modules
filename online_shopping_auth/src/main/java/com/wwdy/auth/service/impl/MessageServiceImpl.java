package com.wwdy.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwdy.auth.config.MessageConfigProperties;
import com.wwdy.auth.enums.MessageResponseEnum;
import com.wwdy.auth.enums.RedisCodePrefixKeyEnum;
import com.wwdy.auth.pojo.dto.SendMessageDTO;
import com.wwdy.auth.request.RpcRequest;
import com.wwdy.auth.request.SendMessageRequest;
import com.wwdy.auth.response.SendMessageResponse;
import com.wwdy.auth.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wwdy
 * @date 2022/2/22 13:48
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_HEADER_VALUE_PREFIX = "APPCODE ";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final RestTemplate restTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final MessageConfigProperties config;


    /**
     * 发送验证码
     * @param sendMessageDTO sendMessageDTO
     * @return SendMessageResponse
     */
    @Override
    public SendMessageResponse send(SendMessageDTO sendMessageDTO) {
        SendMessageRequest request = new SendMessageRequest();
        if (StrUtil.isNotEmpty(config.getTemplateId())) {
            request.setTemplateId(config.getTemplateId());
        }
        if (StrUtil.isNotEmpty(config.getExpireMinutes())) {
            request.setLimitMinutes(config.getExpireMinutes());
        }
        request.setCode(sendMessageDTO.getCode());
        request.setMobile(sendMessageDTO.getPhone());
        request.setTemplateParamSet(request.getCode()+","+request.getLimitMinutes());
        SendMessageResponse response = doPostAction(request);
        if(response.getCode().equals(MessageResponseEnum.SUCCESS.getCode())){
            stringRedisTemplate.opsForValue().set(sendMessageDTO.getKey() + sendMessageDTO.getPhone(), sendMessageDTO.getCode(), Long.parseLong(request.getLimitMinutes()), TimeUnit.MINUTES);
            stringRedisTemplate.opsForValue().set(RedisCodePrefixKeyEnum.SEND_CODE_LIMIT_FREQUENCY.getKey() + sendMessageDTO.getPhone(), sendMessageDTO.getCode(), 1L, TimeUnit.MINUTES);
        }
        log.info("[send code success] =====> send status:{}",response);
        return response;
    }

    protected <T> T doPostAction(RpcRequest<T> request) {
        String response = this.doPostActionForString(config.getUrl(), request,config.getAppcode());
        log.info("[do-post-action] 响应结果: request = {}, response = {}", request, response);
        try {
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(response, request.getResponseClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String doPostActionForString(String url, RpcRequest<?> request, String appCode) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, Object> params = BeanUtil.beanToMap(request);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.setAll(params);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add(AUTH_HEADER, AUTH_HEADER_VALUE_PREFIX + appCode);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, httpHeaders);
        return restTemplate.postForObject(url, httpEntity, String.class);

    }
}
