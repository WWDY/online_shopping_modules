package com.wwdy.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.admin.exception.NotFoundRecordException;
import com.wwdy.admin.mapper.NoticeMapper;
import com.wwdy.admin.pojo.Notice;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author wwdy
 * @date 2022/4/8 21:48
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    /**
     * 添加公告
     *
     * @param notice 公告
     * @return int
     */
    @Override
    public int addNotice(Notice notice) {
        return baseMapper.insert(notice);
    }

    /**
     * 通过id删除公告
     *
     * @param id 公告id
     * @return int
     */
    @Override
    public int delNotice(Integer id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 通过ID查找公告
     * @param id id
     * @return Notice
     */
    @Override
    public Notice selectNoticeById(Integer id) {
        Notice notice = baseMapper.selectById(id);
        if(Optional.ofNullable(notice).isPresent()){
            return notice;
        }
        throw new NotFoundRecordException("商品不存在");
    }

    /**
     * 更新公告内容
     *
     * @param notice 公告
     * @return int
     */
    @Override
    public int updateNotice(Notice notice) {
        return baseMapper.updateById(notice);
    }

    /**
     * 分页查找公告
     *
     * @param pageDTO 查询信息
     * @return List<Notice>
     */
    @Override
    public Page<Notice> selectShopPage(PageDTO pageDTO) {
        Page<Notice> noticePage = new Page<>();
        noticePage.setSize(pageDTO.getSize());
        noticePage.setCurrent(pageDTO.getPage());
        return baseMapper.selectPage(noticePage, null);
    }

    /**
     * 获取权重最高的一条公告
     * @return Notice
     */
    @Override
    public Notice selectNoticeTop1DepWeight() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("weight");
        queryWrapper.last("limit 1");
        return baseMapper.selectOne(queryWrapper);
    }
}




