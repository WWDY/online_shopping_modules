package com.wwdy.oss.controller;

import cn.hutool.core.util.StrUtil;
import com.wwdy.oss.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ResultUtil;
import result.vo.ResultVO;

/**
 * @author wwdy
 * @date 2022/3/16 13:52
 */
@RequiredArgsConstructor
@RequestMapping("/api/object")
@RestController
public class OssObjectController {

    private final OssService ossService;

    /**
     * 删除文件
     * @param fileName 文件完整路径
     * @return ResultVO<String>
     */
    @DeleteMapping("/{filePath}/{fileName}")
    public ResultVO<String> deleteObject(@PathVariable(value = "filePath",required = false)String filePath,
                                         @PathVariable("fileName")String fileName){

        String file = fileName;

        if(StrUtil.isNotEmpty(filePath)){
            file = filePath + "/" + fileName;
        }
        ossService.delete(file);
        return ResultUtil.success("删除成功:" + file);
    }
}
