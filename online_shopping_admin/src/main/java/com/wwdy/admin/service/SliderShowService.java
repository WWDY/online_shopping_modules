package com.wwdy.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.admin.pojo.SliderShow;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.SliderShowUpdate;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/19 16:25
 */
public interface SliderShowService extends IService<SliderShow> {

    /**
     * 添加轮播图
     * @param sliderShow 轮播图信息
     * @return int
     */
    int addSliderShow(SliderShow sliderShow);

    /**
     * 通过id查找轮播图
     * @param id id
     * @return SliderShow
     */
    SliderShow selectSliderShowById(int id);

    /**
     * 分页查找轮播图
     * @param pageDTO 查询信息
     * @return List<SliderShow>
     */
    Page<SliderShow> selectSliderShowPage(PageDTO pageDTO);

    /**
     * 更新轮播图
     * @param update 更新信息
     * @return int
     */
    int updateSliderShow(SliderShowUpdate update);

    /**
     * 通过id删除轮播图
     * @param id id
     * @return int
     */
    int delSliderShow(int id);

    /**
     * 获取权重最高的三张轮播图
     * @return List<SliderShow>
     */
    List<SliderShow> getThreeSliderShowsByWeight();
}
