package com.wwdy.front.feign;

import com.wwdy.front.feign.resource.AuthResource;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wwdy
 * @date 2022/4/3 13:32
 */
@FeignClient("online-shopping-auth")
public interface AuthClient extends AuthResource {
}
