package com.wwdy.front.feign.resource;

import com.wwdy.front.feign.service.CategoryService;
import com.wwdy.front.feign.service.ShopService;
import com.wwdy.front.feign.service.SpuService;

/**
 * @author wwdy
 * @date 2022/4/3 16:01
 */
public interface AdminResource extends ShopService, SpuService, CategoryService {
}
