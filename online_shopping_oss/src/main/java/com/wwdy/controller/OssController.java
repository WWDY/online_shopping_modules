package com.wwdy.controller;

import com.wwdy.pojo.dto.OssDTO;
import com.wwdy.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ResultUtil;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/3/14 12:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signature")
public class OssController {

    private final OssService ossService;

    @GetMapping("/")
    public ResultVO<OssDTO> getSignature(){
        OssDTO signature = ossService.signature();
        return ResultUtil.success(signature);
    }
}
