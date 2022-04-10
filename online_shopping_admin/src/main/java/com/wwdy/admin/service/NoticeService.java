package com.wwdy.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.admin.pojo.Notice;
import com.wwdy.admin.pojo.dto.PageDTO;

/**
 * @author  wwdy
 * @date  2022/4/8 21:46
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 添加公告
     * @param notice 公告
     * @return int
     */
    int addNotice(Notice notice);

    /**
     * 通过id删除公告
     * @param id 公告id
     * @return int
     */
    int delNotice(Integer id);

    /**
     * 通过ID查找公告
     * @param id id
     * @return Notice
     */
    Notice selectNoticeById(Integer id);

    /**
     * 更新公告内容
     * @param notice 公告
     * @return int
     */
    int updateNotice(Notice notice);

    /**
     * 分页查找公告
     * @param pageDTO 查询信息
     * @return List<Notice>
     */
    Page<Notice> selectShopPage(PageDTO pageDTO);

    /**
     * 获取权重最高的一条公告
     * @return Notice
     */
    Notice selectNoticeTop1DepWeight();

}
