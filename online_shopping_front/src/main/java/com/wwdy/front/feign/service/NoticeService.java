package com.wwdy.front.feign.service;

import com.wwdy.front.feign.pojo.dto.Notice;
import org.springframework.web.bind.annotation.GetMapping;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/4/8 23:31
 */
public interface NoticeService {

    /**
     * 获取权重最高的一条公告
     * @return ResultVO<Notice>
     */
    @GetMapping("/api/notice/top1")
    ResultVO<Notice> getTop1();
}
