package com.wwdy.front.controller;

import com.wwdy.front.pojo.dto.ShopPayInfo;
import com.wwdy.front.service.AlipayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.ResultVO;

import java.util.Map;

/**
 * @author wwdy
 * @date 2022/4/8 21:09
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/alipay")
public class AlipayController {

    private final AlipayService alipayService;

    @PostMapping(value = "/pay")
    public ResultVO<String> gotoAlipay(@RequestBody ShopPayInfo shopPayInfos) {
        String payForm = alipayService.pay(shopPayInfos);
        return ResultUtil.success(payForm,"");
    }

    @GetMapping(value = "/return", produces = "text/html;charset=UTF-8")
    public String getReturnInfo(@RequestParam(required = false) Map<String,String> params){
        boolean checkPay = alipayService.checkPay(params);
        return "<meta http-equiv=\"refresh\" content=\"3;url=http://localhost:3000\">";
    }

}
