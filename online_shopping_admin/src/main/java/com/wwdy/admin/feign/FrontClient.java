package com.wwdy.admin.feign;

import com.wwdy.admin.config.OpenFeignRequestConfig;
import com.wwdy.admin.feign.resource.OrderResource;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wwdy
 * @date 2022/4/10 15:44
 */
@FeignClient(name = "online-shopping-front",configuration = OpenFeignRequestConfig.class)
public interface FrontClient extends OrderResource {
}
