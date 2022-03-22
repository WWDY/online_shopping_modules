package com.wwdy.admin.feign.resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wwdy
 * @date 2022/3/21 11:24
 */
public interface OssResource {

    /**
     * 删除oss 对象
     * @param filePath 文件夹
     * @param fileName 问价名称
     */
    @DeleteMapping("/api/object/{filePath}/{fileName}")
    void delOssObject(@PathVariable(value = "filePath", required = false) String filePath,
                      @PathVariable("fileName") String fileName);
}
