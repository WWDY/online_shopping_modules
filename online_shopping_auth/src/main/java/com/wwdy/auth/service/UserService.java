package com.wwdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.auth.pojo.UserDO;
import com.wwdy.auth.pojo.dto.LoginByPhoneDTO;
import com.wwdy.auth.pojo.dto.LoginDTO;
import com.wwdy.auth.pojo.dto.UserDTO;

/**
 * @author wwdy
 * @date 2022/2/22 12:40
 */
public interface UserService extends IService<UserDO> {

    /**
     * 用户注册
     * @param user 用户信息
     * @return boolean
     */
    boolean register(UserDTO user);

    /**
     * 发送验证码
     * @param phone 手机号码
     * @param key redisCodePrefixKey
     * @return String
     */
    String sendCode(String phone, String key);

    /**
     * 登录-账号密码登录
     * @param loginDTO 登录信息
     * @return token
     */
    String login(LoginDTO loginDTO);

    /**
     * 登录-验证码登录
     * @param login 登录信息
     * @return token
     */
    String login(LoginByPhoneDTO login);

    /**
     * 单点登录
     * @return token
     */
    String sso();

    /**
     * 注销登录
     */
    void logout();

    /**
     * 判断手机号是否被注册
     * @param phone 手机号
     * @return boolean
     */
    boolean phoneIsExist(String phone);
}
