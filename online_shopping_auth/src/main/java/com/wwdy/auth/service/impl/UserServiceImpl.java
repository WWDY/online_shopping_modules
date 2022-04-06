package com.wwdy.auth.service.impl;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.auth.config.JwtConfigProperties;
import com.wwdy.auth.converter.UserConverter;
import com.wwdy.auth.enums.MessageResponseEnum;
import com.wwdy.auth.enums.RedisCodePrefixKeyEnum;
import com.wwdy.auth.event.DelUsedCodeEvent;
import com.wwdy.auth.exception.CodeErrorException;
import com.wwdy.auth.mapper.UserMapper;
import com.wwdy.auth.pojo.UserDO;
import com.wwdy.auth.pojo.dto.LoginByPhoneDTO;
import com.wwdy.auth.pojo.dto.LoginDTO;
import com.wwdy.auth.pojo.dto.SendMessageDTO;
import com.wwdy.auth.pojo.dto.UserDTO;
import com.wwdy.auth.response.SendMessageResponse;
import com.wwdy.auth.service.MessageService;
import com.wwdy.auth.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static constant.JwtConstant.*;

/**
 * @author wwdy
 * @date 2022/2/22 12:40
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDO> implements UserService{

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final StringRedisTemplate stringRedisTemplate;
    private final JwtConfigProperties jwtConfigProperties;
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;
    private final UserConverter userConverter;
    private final MessageService messageService;
    private final ApplicationContext applicationContext;

    private static final String SSO_TOKEN_COOKIE_NAME = "SSO-Token";
    private static final String PHONE_REG = "^(?:(?:\\+|00)86)?1(?:3[\\d]|4[5-7|9]|5[0-3|5-9]|6[5-7]|7[0-8]|8[\\d]|9[1|89])\\d{8}$";


    /**
     * 用户注册
     * @param user 用户信息
     * @return boolean
     */
    @Override
    public boolean register(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String code = stringRedisTemplate.opsForValue().get(RedisCodePrefixKeyEnum.REGISTER_CODE.getKey() + user.getPhone());
        if(StrUtil.equals(code,user.getCode())){
            applicationContext.publishEvent(
                    new DelUsedCodeEvent(
                            DelUsedCodeEvent.DelUsedCodeEventData.builder()
                                    .keyPrefix(RedisCodePrefixKeyEnum.REGISTER_CODE.getKey())
                                    .phone(user.getPhone())
                                    .code(user.getCode())
                                    .build()
                    )
            );
            return save(userConverter.convert(user));
        }
        throw new CodeErrorException("验证码错误");
    }

    /**
     * 发送验证码
     * @param phone 手机号码
     * @param key redisCodePrefixKey
     * @return String
     */
    @Override
    public String sendCode(String phone, String key) {
        if (StrUtil.equals(key,RedisCodePrefixKeyEnum.LOGIN_CODE.getKey())) {
            UserDO user = baseMapper.selectOne(new QueryWrapper<UserDO>().eq("phone", phone));
            if(!Optional.ofNullable(user).isPresent()){
                return MessageResponseEnum.PHONE_NOT_EXIST.getCode();
            }
        }
        if(Optional.ofNullable(stringRedisTemplate.opsForValue().get(RedisCodePrefixKeyEnum.SEND_CODE_LIMIT_FREQUENCY.getKey() + phone)).isPresent()){
            return MessageResponseEnum.SEND_FREQUENTLY.getCode();
        }
        if(!ReUtil.isMatch(PHONE_REG,phone)){
            return MessageResponseEnum.PHONE_ERROR.getCode();
        }
        Random random = new Random();
        int code = 1000 + random.nextInt(8999);
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setCode(String.valueOf(code));
        sendMessageDTO.setPhone(phone);
        sendMessageDTO.setKey(key);
        SendMessageResponse response = messageService.send(sendMessageDTO);
        if (StrUtil.equals(response.getCode(), MessageResponseEnum.SUCCESS.getCode())) {
            return MessageResponseEnum.SUCCESS.getCode();
        }
        return null;
    }

    /**
     * 登录-账号密码登录
     * @param loginDTO 登录信息
     * @return token
     */
    @Override
    public String login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        UserDO user = baseMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
        if (Optional.ofNullable(user).isPresent()) {
            if(passwordEncoder.matches(loginDTO.getPassword(),user.getPassword())){
                return signToken(user);
            }
        }
        return null;
    }

    /**
     * 登录-验证码登录
     * @param login 登录信息
     * @return token
     */
    @Override
    public String login(LoginByPhoneDTO login) {
        String code = stringRedisTemplate.opsForValue().get(RedisCodePrefixKeyEnum.LOGIN_CODE.getKey() + login.getPhone());
        if (StrUtil.isNotEmpty(code) && StrUtil.equals(code,login.getCode())) {
            UserDO user = baseMapper.selectOne(new QueryWrapper<UserDO>().eq("phone", login.getPhone()));
            applicationContext.publishEvent(
                    new DelUsedCodeEvent(
                            DelUsedCodeEvent.DelUsedCodeEventData.builder()
                                    .keyPrefix(RedisCodePrefixKeyEnum.LOGIN_CODE.getKey())
                                    .phone(login.getPhone())
                                    .code(login.getCode())
                                    .build()
                    )
            );
            return signToken(user);
        }
        return null;
    }

    /**
     * 单点登录
     * @return token
     */
    @Override
    public String sso() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(StrUtil.equals(cookie.getName(),SSO_TOKEN_COOKIE_NAME)){
                String token = cookie.getValue();
                try {
                    Claims claims = jwtParser.parseClaimsJws(token).getBody();
                    String username = claims.getId();
                    UserDO user = baseMapper.selectById(username);
                    if (Optional.ofNullable(user).isPresent()) {
                        String key = JWT_TOKEN_PREFIX + user.getId() + "-" + user.getUsername() + "-" + user.getPhone();
                        String redisToken = stringRedisTemplate.opsForValue().get(key);
                        if (StrUtil.isEmpty(redisToken)) {
                            return null;
                        }
                        if(StrUtil.equals(token,redisToken)){
                            return redisToken;
                        }
                    }
                }catch (SecurityException ex) {
                    log.warn("Invalid JWT signature: token = {}", token);
                } catch (MalformedJwtException ex) {
                    log.warn("Invalid JWT token: token = {}", token);
                } catch (UnsupportedJwtException ex) {
                    log.warn("Unsupported JWT token: token = {}", token);
                } catch (IllegalArgumentException ex) {
                    log.warn("JWT claims string is empty: token = {}", token);
                } catch (ExpiredJwtException ex) {
                    log.warn("JWT is expired: token = {}", token);
                }
            }
        }
        return null;
    }

    /**
     * 注销登录
     */
    @Override
    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String value = request.getHeader(HEADER_NAME);
        if(StrUtil.isNotEmpty(value)){
            String token = value.substring(HEADER_START.length());
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getId();
            UserDO user = baseMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
            if(Optional.ofNullable(user).isPresent()){
                String key = JWT_TOKEN_PREFIX + user.getId() + "-" + user.getUsername() + "-" + user.getPhone();
                stringRedisTemplate.delete(key);
            }
        }
    }

    /**
     * 判断手机号是否被注册
     * @param phone 手机号
     * @return boolean
     */
    @Override
    public boolean phoneIsExist(String phone) {
        return baseMapper.selectOne(new QueryWrapper<UserDO>().eq("phone",phone)) != null;
    }

    /**
     * 下发令牌
     * @return token
     */
    protected String signToken(UserDO user){
        Claims claims = Jwts.claims()
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperties.getJwtExpirationInMs()))
                .setId(String.valueOf(user.getId()))
                .setSubject("login")
                .setIssuedAt(new Date());
        String token = jwtBuilder.setClaims(claims).compact();
        String key = JWT_TOKEN_PREFIX + user.getId() + "-" + user.getUsername() + "-" + user.getPhone();
        stringRedisTemplate.opsForValue().set(key, token, jwtConfigProperties.getJwtExpirationInMs(), TimeUnit.MILLISECONDS);
        //添加登录凭证
        Cookie cookie = new Cookie(SSO_TOKEN_COOKIE_NAME,token);
        cookie.setPath("/");
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        assert response != null;
        response.addCookie(cookie);
        return token;
    }

    /**
     * 通过token获取用户信息
     * @param token token
     * @return UserDO
     */
    @Override
    public UserDO getUserByToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getId();
            UserDO user = baseMapper.selectById(username);
            if (Optional.ofNullable(user).isPresent()) {
                return user;
            }
        }catch (SecurityException ex) {
            log.warn("Invalid JWT signature: token = {}", token);
        } catch (MalformedJwtException ex) {
            log.warn("Invalid JWT token: token = {}", token);
        } catch (UnsupportedJwtException ex) {
            log.warn("Unsupported JWT token: token = {}", token);
        } catch (IllegalArgumentException ex) {
            log.warn("JWT claims string is empty: token = {}", token);
        } catch (ExpiredJwtException ex) {
            log.warn("JWT is expired: token = {}", token);
        }
        return null;
    }

    /**
     * 签发openfeign token
     * @return String
     */
    @Override
    public String openFeignTokenSignature() {
        String redisToken = stringRedisTemplate.opsForValue().get(FEIGN_TOKEN_HEADER_NAME);
        if(StrUtil.isNotEmpty(redisToken)){
            return redisToken;
        }
        Claims claims = Jwts.claims()
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperties.getJwtExpirationInMs()))
                .setSubject("openfeign")
                .setIssuedAt(new Date());
        String token = jwtBuilder.setClaims(claims).compact();
        stringRedisTemplate.opsForValue().set(FEIGN_TOKEN_HEADER_NAME, token, jwtConfigProperties.getJwtExpirationInMs(), TimeUnit.MILLISECONDS);
        return token;
    }
}
