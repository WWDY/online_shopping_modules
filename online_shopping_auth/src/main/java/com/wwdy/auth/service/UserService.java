package com.wwdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.auth.pojo.UserDO;
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
     * 登录
     * @param loginDTO 登录信息
     * @return token
     */
    String login(LoginDTO loginDTO);

    /**
     * 单点登录
     * @return token
     */
    String sso();

    /**
     * 注销登录
     */
    void logout();
}
