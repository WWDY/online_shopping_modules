package com.wwdy.admin.event.listener;

import com.wwdy.admin.event.DelSliderShowEvent;
import com.wwdy.admin.feign.OssClient;
import com.wwdy.admin.pojo.SliderShow;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author wwdy
 * @date 2022/3/21 11:18
 */
@Component
@RequiredArgsConstructor
public class DelSliderShowListener {

    private final OssClient ossClient;

    private static final String PREFIX = "https://wwdy-online-shopping.oss-cn-beijing.aliyuncs.com/";

    @EventListener
    public void delSliderShow(DelSliderShowEvent delSliderShowEvent) {
        SliderShow sliderShow = delSliderShowEvent.getSliderShow();
        String url = sliderShow.getUrl();
        String path = url.substring(PREFIX.length());
        String[] split = path.split("/");
        ossClient.delOssObject(split[0],split[1]);
    }
}
