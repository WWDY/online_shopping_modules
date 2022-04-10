package com.wwdy.front.controller;

import com.wwdy.front.feign.AdminClient;
import com.wwdy.front.feign.pojo.dto.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/4/8 23:34
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notice")
public class NoticeService {

    private final AdminClient adminClient;

    /**
     * 获取权重最高的一条公告
     * @return ResultVO<Notice>
     */
    @GetMapping("/top1")
    public ResultVO<Notice> getTop1(){
        return adminClient.getTop1();
    }
}
