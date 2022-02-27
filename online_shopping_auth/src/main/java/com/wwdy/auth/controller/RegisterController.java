package com.wwdy.auth.controller;

import cn.hutool.core.util.ReUtil;
import com.wwdy.auth.enums.MessageResponseEnum;
import com.wwdy.auth.pojo.dto.SendMessageDTO;
import com.wwdy.auth.pojo.dto.UserDTO;
import com.wwdy.auth.response.SendMessageResponse;
import com.wwdy.auth.service.MessageService;
import com.wwdy.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.ResultVO;

import javax.lang.model.type.NullType;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Random;

/**
 * @author wwdy
 * @date 2022/2/21 16:10
 */
@RequestMapping("/api/register")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;
    private final MessageService messageService;

    private static final String PHONE_REG = "^(?:(?:\\+|00)86)?1(?:3[\\d]|4[5-7|9]|5[0-3|5-9]|6[5-7]|7[0-8]|8[\\d]|9[1|89])\\d{8}$";


    /**
     * 用户注册
     * @param user 注册信息
     * @return ResultVO
     */
    @PostMapping("/")
    public ResultVO<NullType> register(@RequestBody @Valid UserDTO user){
        if (userService.register(user)) {
            return ResultUtil.success("注册成功");
        }
        return ResultUtil.error("注册失败");
    }

    /**
     * 获取短信验证码
     * @param phone 手机号
     * @param request HttpServletRequest
     * @return ResultVO<String>
     */
    @GetMapping("/code")
    public ResultVO<String> sendCode(@RequestParam("phone")String phone, HttpServletRequest request){
        if(Optional.ofNullable(stringRedisTemplate.opsForValue().get(request.getSession().getId())).isPresent()){
            return ResultUtil.error("发送频繁，请稍后再试");
        }
        if(!ReUtil.isMatch(PHONE_REG,phone)){
            return ResultUtil.error("非法格式手机号码");
        }
        Random random = new Random();
        int code = 1000 + random.nextInt(8999);
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setCode(String.valueOf(code));
        sendMessageDTO.setPhone(phone);
        sendMessageDTO.setSessionId(request.getSession().getId());
        SendMessageResponse response = messageService.send(sendMessageDTO);
        if(response.getCode().equals(MessageResponseEnum.SUCCESS.getCode())){
            return ResultUtil.success(MessageResponseEnum.SUCCESS.getMsg());
        }
        return ResultUtil.error(response.getMsg());
    }

}
