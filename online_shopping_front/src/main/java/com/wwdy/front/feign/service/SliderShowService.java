package com.wwdy.front.feign.service;

import com.wwdy.front.feign.pojo.dto.SliderShow;
import org.springframework.web.bind.annotation.GetMapping;
import result.vo.ResultVO;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/6 16:21
 */
public interface SliderShowService {

    /**
     * 获取权重最高的三张轮播图
     * @return ResultVO<List<SliderShow>>
     */
    @GetMapping("/api/slider-show/top3")
    ResultVO<List<SliderShow>> getTop3ByWeight();
}
