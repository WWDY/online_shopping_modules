package com.wwdy.admin.feign;

import com.wwdy.admin.feign.resource.UserResource;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wwdy
 * @date 2022/3/20 12:54
 */
@FeignClient("online-shopping-auth")
public interface UserClient extends UserResource {
}
