package com.wwdy.admin.feign;

import com.wwdy.admin.feign.resource.OssResource;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wwdy
 * @date 2022/3/21 11:26
 */
@FeignClient("online-shopping-oss")
public interface OssClient extends OssResource {
}
