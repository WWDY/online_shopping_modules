package com.wwdy.auth.service;

import com.wwdy.auth.pojo.dto.SendMessageDTO;
import com.wwdy.auth.response.SendMessageResponse;

/**
 * @author wwdy
 * @date 2022/2/22 13:48
 */
public interface MessageService {

    /**
     * 发送验证码
     * @param sendMessageDTO sendMessageDTO
     * @return SendMessageResponse
     */
    SendMessageResponse send(SendMessageDTO sendMessageDTO);
}
