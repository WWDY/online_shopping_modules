package com.wwdy.front.controller;

import com.wwdy.front.feign.AdminClient;
import com.wwdy.front.feign.pojo.dto.SliderShow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.vo.ResultVO;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/6 16:22
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/slider-show")
public class SliderShowController {

    private final AdminClient adminClient;

    @GetMapping("/top3")
    public ResultVO<List<SliderShow>> getTop3ByWeight(){
        return adminClient.getTop3ByWeight();
    }
}
