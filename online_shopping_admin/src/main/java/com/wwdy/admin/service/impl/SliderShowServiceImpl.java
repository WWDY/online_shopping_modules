package com.wwdy.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.admin.converter.SliderShowConverter;
import com.wwdy.admin.exception.NotFoundRecordException;
import com.wwdy.admin.mapper.SliderShowMapper;
import com.wwdy.admin.pojo.SliderShow;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.SliderShowUpdate;
import com.wwdy.admin.service.SliderShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author  wwdy
 * @date  2022/3/19 16:25
 */
@Service
@RequiredArgsConstructor
public class SliderShowServiceImpl extends ServiceImpl<SliderShowMapper, SliderShow> implements SliderShowService{

    private final SliderShowConverter sliderShowConverter;
    private final ApplicationContext applicationContext;

    /**
     * 添加轮播图
     * @param sliderShow 轮播图信息
     * @return int
     */
    @Override
    public int addSliderShow(SliderShow sliderShow) {
        return baseMapper.insert(sliderShow);
    }

    /**
     * 通过id查找轮播图
     *
     * @param id id
     * @return SliderShow
     */
    @Override
    public SliderShow selectSliderShowById(int id) {
        SliderShow sliderShow = baseMapper.selectById(id);
        if(Optional.ofNullable(sliderShow).isPresent()){
            return sliderShow;
        }
        throw new NotFoundRecordException("轮播图不存在");
    }

    /**
     * 分页查找轮播图
     * @param pageDTO 查询信息
     * @return List<SliderShow>
     */
    @Override
    public Page<SliderShow> selectSliderShowPage(PageDTO pageDTO) {
        Page<SliderShow> sliderShowPage = new Page<>();
        sliderShowPage.setSize(pageDTO.getSize());
        sliderShowPage.setCurrent(pageDTO.getPage());
        return baseMapper.selectPage(sliderShowPage, null);
    }

    /**
     * 更新轮播图
     * @param update 更新信息
     * @return int
     */
    @Override
    public int updateSliderShow(SliderShowUpdate update) {
        SliderShow convert = sliderShowConverter.convert(update);
        return baseMapper.updateById(convert);
    }

    /**
     * 通过id删除轮播图
     * @param id id
     * @return int
     */
    @Override
    public int delSliderShow(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 获取权重最高的三张轮播图
     * @return List<SliderShow>
     */
    @Override
    public List<SliderShow> getThreeSliderShowsByWeight() {
        QueryWrapper<SliderShow> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("weight")
                .last("limit 0,3");
        return baseMapper.selectList(queryWrapper);
    }
}
