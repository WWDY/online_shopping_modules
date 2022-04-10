package com.wwdy.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwdy.admin.pojo.SliderShow;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.SliderShowUpdate;
import com.wwdy.admin.service.SliderShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wwdy
 * @date 2022/3/19 16:26
 */
@RestController
@RequestMapping("/api/slider-show")
@RequiredArgsConstructor
public class SliderShowController {

    private final SliderShowService sliderShowService;

    /**
     * 添加轮播图
     * @param sliderShow 轮播图信息
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> addSliderShow(@Valid @RequestBody SliderShow sliderShow){
        int res = sliderShowService.addSliderShow(sliderShow);
        if (res > 0) {
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error("添加失败");
    }

    /**
     * 通过id查找轮播图
     *
     * @param id id
     * @return ResultVO<SliderShow>
     */
    @GetMapping("/{id}")
    public ResultVO<SliderShow> getSliderShowById(@PathVariable("id") Integer id){
        SliderShow sliderShow = sliderShowService.selectSliderShowById(id);
        return ResultUtil.success(sliderShow);
    }

    /**
     * 分页查找轮播图
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<SliderShow>>
     */
    @GetMapping("/")
    public ResultVO<PageVO<SliderShow>> getSliderShowPage(@Valid PageDTO pageDTO){
        Page<SliderShow> page = sliderShowService.selectSliderShowPage(pageDTO);
        PageVO<SliderShow> pageVO = PageVO.of(page.getRecords(), pageDTO.getPage(), pageDTO.getSize(), page.getTotal());
        return ResultUtil.success(pageVO);
    }

    /**
     * 更新轮播图
     * @param update 更新内容
     * @return ResultVO<String>
     */
    @PatchMapping("/")
    public ResultVO<String> updateSliderShow(@Valid @RequestBody SliderShowUpdate update) {
        int res = sliderShowService.updateSliderShow(update);
        if (res > 0) {
            return ResultUtil.success("更新成功");
        }
        return ResultUtil.error("更新失败");
    }

    /**
     * 通过id删除轮播图
     * @param id id
     * @return ResultVO<String>
     */
    @DeleteMapping("/{id}")
    public ResultVO<String> delSliderShow(@PathVariable("id") Integer id){
        int res = sliderShowService.delSliderShow(id);
        if (res > 0) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error("删除失败");
    }

    /**
     * 获取权重最高的三张轮播图
     * @return ResultVO<List<SliderShow>>
     */
    @GetMapping("/top3")
    public ResultVO<List<SliderShow>> getTop3ByWeight(){
        List<SliderShow> res = sliderShowService.getThreeSliderShowsByWeight();
        return ResultUtil.success(res);
    }
}
