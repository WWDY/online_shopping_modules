package com.wwdy.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwdy.admin.pojo.Notice;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.ResultUtil;
import result.vo.PageVO;
import result.vo.ResultVO;

import javax.validation.Valid;

/**
 * @author  wwdy
 * @date  2022/3/22 15:35
 */
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 添加公告
     * @param notice 公告信息
     * @return ResultVO<String>
     */
    @PostMapping("/")
    public ResultVO<String> addNotice(@Valid @RequestBody Notice notice){
        int res = noticeService.addNotice(notice);
        if (res > 0) {
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error("添加失败");
    }

    /**
     * 通过id查找公告
     *
     * @param id id
     * @return ResultVO<Notice>
     */
    @GetMapping("/{id}")
    public ResultVO<Notice> getNoticeById(@PathVariable("id") Integer id){
        Notice notice = noticeService.selectNoticeById(id);
        return ResultUtil.success(notice);
    }

    /**
     * 分页查找公告
     * @param pageDTO 分页信息
     * @return ResultVO<PageVO<Notice>>
     */
    @GetMapping("/")
    public ResultVO<PageVO<Notice>> getNoticePage(@Valid PageDTO pageDTO){
        Page<Notice> noticePage = noticeService.selectShopPage(pageDTO);
        PageVO<Notice> pageVO = PageVO.of(noticePage.getRecords(), pageDTO.getPage(), pageDTO.getSize(), noticePage.getTotal());
        return ResultUtil.success(pageVO);
    }

    /**
     * 更新公告
     * @param update 更新内容
     * @return ResultVO<String>
     */
    @PatchMapping("/")
    public ResultVO<String> updateNotice(@Valid @RequestBody Notice update) {
        int res = noticeService.updateNotice(update);
        if (res > 0) {
            return ResultUtil.success("更新成功");
        }
        return ResultUtil.error("更新失败");
    }

    /**
     * 通过id删除公告
     * @param id id
     * @return ResultVO<String>
     */
    @DeleteMapping("/{id}")
    public ResultVO<String> delNotice(@PathVariable("id") Integer id){
        int res = noticeService.delNotice(id);
        if (res > 0) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error("删除失败");
    }

    /**
     * 获取权重最高的一条公告
     * @return ResultVO<Notice>
     */
    @GetMapping("/top1")
    public ResultVO<Notice> getTop1(){
        Notice notice = noticeService.selectNoticeTop1DepWeight();
        return ResultUtil.success(notice);
    }

}
