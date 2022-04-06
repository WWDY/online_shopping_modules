package com.wwdy.front.controller;

import com.wwdy.front.feign.AdminClient;
import com.wwdy.front.feign.pojo.dto.Spu;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.vo.ResultVO;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/3 15:49
 */
@RestController
@RequestMapping("/api/spu")
@RequiredArgsConstructor
public class SpuController {

    private final AdminClient adminClient;

    /**
     * 通过id查找SPU
     *
     * @param id id
     * @return ResultVO<Spu>
     */
    @GetMapping("/{id}")
    public ResultVO<Spu> getSpuById(@PathVariable("id") Integer id){
        return adminClient.getSpuById(id);
    }

    /**
     * 获取指定id的spu信息
     * @param ids id
     * @return List<Spu>
     */
    @PostMapping("/ids")
    public ResultVO<List<Spu>> getSpuByIds(@RequestBody List<Integer> ids){
        return adminClient.getSpuByIds(ids);
    }
}
