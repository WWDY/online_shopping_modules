package com.wwdy.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwdy.admin.pojo.Spu;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.SpuUpdate;
import com.wwdy.admin.pojo.vo.SpuVO;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/22 10:42
 */
public interface SpuService extends IService<Spu> {

    /**
     * 添加SPU
     * @param spu SPU信息
     * @return int
     */
    int addSpu(Spu spu);

    /**
     * 通过id查找SPU
     * @param id id
     * @return Spu
     */
    Spu selectSpuById(int id);

    /**
     * 分页查找SPU
     * @param pageDTO 查询信息
     * @return List<Spu>
     */
    Page<SpuVO> selectSpuPage(PageDTO pageDTO);

    /**
     * 更新SPU
     * @param update 更新信息
     * @return int
     */
    int updateSpu(SpuUpdate update);

    /**
     * 通过id删除SPU
     * @param id id
     * @return int
     */
    int delSpu(int id);

    /**
     * 获取所有spu
     * @return List<SpuVO>
     */
    List<SpuVO> getAll();

}
