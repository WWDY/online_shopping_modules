package com.wwdy.front.feign.resource;

import com.wwdy.front.feign.service.*;

/**
 * @author wwdy
 * @date 2022/4/3 16:01
 */
public interface AdminResource extends ShopService, SpuService, CategoryService, SliderShowService, NoticeService {
}
