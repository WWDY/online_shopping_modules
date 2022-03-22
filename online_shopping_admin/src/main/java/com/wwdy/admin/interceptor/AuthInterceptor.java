package com.wwdy.admin.interceptor;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwdy.admin.feign.UserClient;
import com.wwdy.admin.feign.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import result.ResultUtil;
import result.enums.ResultEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static constant.JwtConstant.*;


/**
 * @author wwdy
 * @date 2022/3/19 17:57
 */
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;
    private final ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @Nullable Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        //OpenFeign 注入时机在WebMvcConfiguration之后，在配置拦截器时会产生循环依赖问题，所以在使用时才获取代理对象
        UserClient userClient = applicationContext.getBean(UserClient.class);
        String value = request.getHeader(HEADER_NAME);
        if(StrUtil.isNotEmpty(value)){
            String token = value.substring(HEADER_START.length());
            User user = userClient.getUser(token).getData();
            if(Optional.ofNullable(user).isPresent()){
                String key = JWT_TOKEN_PREFIX + user.getId() + "-" + user.getUsername() + "-" + user.getPhone();
                String redisToken = stringRedisTemplate.opsForValue().get(key);
                if (StrUtil.isNotEmpty(redisToken)) {
                    return true;
                }
            }
        }
        String res = objectMapper.writeValueAsString(ResultUtil.error(ResultEnum.AUTHENTICATION.getCode(), ResultEnum.AUTHENTICATION.getMessage()));
        response.getWriter().println(res);
        return false;
    }
}
