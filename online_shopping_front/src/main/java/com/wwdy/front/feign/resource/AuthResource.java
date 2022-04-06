package com.wwdy.front.feign.resource;

import com.wwdy.front.feign.service.OpenFeignService;
import com.wwdy.front.feign.service.UserService;

/**
 * @author wwdy
 * @date 2022/4/4 13:23
 */
public interface AuthResource extends UserService, OpenFeignService {
}
