package com.wwdy.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.auth.config.JwtConfigProperties;
import com.wwdy.auth.converter.UserConverter;
import com.wwdy.auth.exception.CodeErrorException;
import com.wwdy.auth.mapper.UserMapper;
import com.wwdy.auth.pojo.UserDO;
import com.wwdy.auth.pojo.dto.LoginDTO;
import com.wwdy.auth.pojo.dto.UserDTO;
import com.wwdy.auth.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    private static final String JWT_TOKEN_PREFIX = "AUTH:LOGIN:";
    private static final String SSO_TOKEN_COOKIE_NAME = "SSO-Token";
    private static final String REDIS_CODE_PREFIX = "AUTH:CODE:";
    public static final String HEADER_NAME = "Authorization";
    public static final String HEADER_START = "Bearer ";


    /**
     * 用户注册
     * @param user 用户信息
     * @return boolean
     */
    @Override
    public boolean register(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String sessionId = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getSessionId();
        String code = stringRedisTemplate.opsForValue().get(REDIS_CODE_PREFIX + sessionId);
        if(StrUtil.equals(code,user.getCode())){
            return save(userConverter.convert(user));
        }
        throw new CodeErrorException("验证码错误");
    }

    /**
     * 登录
     * @param loginDTO 登录信息
     * @return token
     */
    @Override
    public String login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        UserDO user = baseMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
        if (Optional.ofNullable(user).isPresent()) {
            if(passwordEncoder.matches(loginDTO.getPassword(),user.getPassword())){
                Claims claims = Jwts.claims()
                        .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperties.getJwtExpirationInMs()))
                        .setId(loginDTO.getUsername())
                        .setSubject("login")
                        .setIssuedAt(new Date());
                String token = jwtBuilder.setClaims(claims).compact();
                String key = JWT_TOKEN_PREFIX + user.getId() + "-" + user.getUsername() + "-" + user.getPhone();
                stringRedisTemplate.opsForValue().set(key, token, jwtConfigProperties.getJwtExpirationInMs(), TimeUnit.MILLISECONDS);
                //添加登录凭证
                Cookie cookie = new Cookie(SSO_TOKEN_COOKIE_NAME,token);
                loginDTO.getResponse().addCookie(cookie);
                return token;
            }
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
                    UserDO user = baseMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
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
}
