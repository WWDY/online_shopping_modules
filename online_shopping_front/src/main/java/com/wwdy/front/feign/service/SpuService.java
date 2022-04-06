package com.wwdy.front.feign.service;

import com.wwdy.front.feign.pojo.dto.Spu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import result.vo.ResultVO;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/4/3 16:00
 */
public interface SpuService {
    /**
     * 通过id查找SPU
     *
     * @param id id
     * @return ResultVO<Spu>
     */
    @GetMapping("/api/spu/{id}")
    ResultVO<Spu> getSpuById(@PathVariable("id") Integer id);

    /**
     * 获取指定id的spu信息
     * @param ids id
     * @return List<Spu>
     */
    @PostMapping("/api/spu/ids")
    ResultVO<List<Spu>> getSpuByIds(@RequestBody List<Integer> ids);

    /**
     * 获取根标签spu信息
     * @return List<Spu>
     */
    @GetMapping("/api/spu/root")
    ResultVO<List<Spu>> getRootSpu();
}
