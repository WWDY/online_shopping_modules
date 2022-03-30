package com.wwdy.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwdy.admin.pojo.Spu;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.SpuUpdate;
import com.wwdy.admin.pojo.vo.SpuVO;
import com.wwdy.admin.service.SpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/22 15:35
 */
@RestController
@RequestMapping("/api/spu")
@RequiredArgsConstructor
public class SpuController {

    private final SpuService spuService;

    /**
     * 添加SPU
     * @param spu SPU信息
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> addSpu(@Valid @RequestBody Spu spu){
        int res = spuService.addSpu(spu);
        if (res > 0) {
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error("添加失败");
    }

    /**
     * 通过id查找SPU
     *
     * @param id id
     * @return ResultVO<Spu>
     */
    @GetMapping("/{id}")
    public ResultVO<Spu> getSpuById(@PathVariable("id") Integer id){
        Spu spu = spuService.selectSpuById(id);
        return ResultUtil.success(spu);
    }

    /**
     * 分页查找SPU
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<Spu>>
     */
    @GetMapping("/")
    public ResultVO<PageVO<SpuVO>> getSpuPage(@Valid PageDTO pageDTO){
        Page<SpuVO> spuPage = spuService.selectSpuPage(pageDTO);
        PageVO<SpuVO> pageVO = PageVO.of(spuPage.getRecords(), pageDTO.getPage(), pageDTO.getSize(), spuPage.getTotal());
        return ResultUtil.success(pageVO);
    }

    /**
     * 更新SPU
     * @param update 更新内容
     * @return ResultVO<String>
     */
    @PatchMapping("/")
    public ResultVO<String> updateSpu(@Valid @RequestBody SpuUpdate update) {
        int res = spuService.updateSpu(update);
        if (res > 0) {
            return ResultUtil.success("更新成功");
        }
        return ResultUtil.error("更新失败");
    }

    /**
     * 通过id删除SPU
     * @param id id
     * @return ResultVO<String>
     */
    @DeleteMapping("/{id}")
    public ResultVO<String> delSpu(@PathVariable("id") Integer id){
        int res = spuService.delSpu(id);
        if (res > 0) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error("删除失败");
    }

    /**
     * 获取所有spu信息
     * @return ResultVO<List<Spu>>
     */
    @GetMapping("/all")
    public ResultVO<List<SpuVO>> getAll(){
        List<SpuVO> all = spuService.getAll();
        return ResultUtil.success(all);
    }
}
