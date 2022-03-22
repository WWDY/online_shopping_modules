package com.wwdy.oss.service;

import com.wwdy.oss.pojo.dto.OssDTO;

/**
 * @author wwdy
 * @date 2022/3/14 11:47
 */
public interface OssService {
    /**
     * 签名
     * @return OssDTO
     */
    OssDTO signature();

    /**
     * 删除文件
     * @param fileName 文件完整路径
     */
    void delete(String fileName);
}
