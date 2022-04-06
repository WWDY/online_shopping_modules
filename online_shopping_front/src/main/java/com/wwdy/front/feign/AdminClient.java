package com.wwdy.front.feign;

import com.wwdy.front.config.OpenFeignRequestConfig;
import com.wwdy.front.feign.resource.AdminResource;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wwdy
 * @date 2022/4/3 11:14
 */
@FeignClient(value = "online-shopping-admin",configuration = OpenFeignRequestConfig.class)
public interface AdminClient extends AdminResource {
}
