package com.wwdy.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwdy.admin.converter.SpuConverter;
import com.wwdy.admin.exception.NotFoundRecordException;
import com.wwdy.admin.mapper.SpuMapper;
import com.wwdy.admin.pojo.Spu;
import com.wwdy.admin.pojo.dto.PageDTO;
import com.wwdy.admin.pojo.update.SpuUpdate;
import com.wwdy.admin.pojo.vo.SpuVO;
import com.wwdy.admin.service.SpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author  wwdy
 * @date  2022/3/22 10:43
 */
@Service
@RequiredArgsConstructor
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService{

    private final SpuConverter spuConverter;

    /**
     * 添加SPU
     * @param spu SPU信息
     * @return int
     */
    @Override
    public int addSpu(Spu spu) {
        return baseMapper.insert(spu);
    }

    /**
     * 通过id查找SPU
     * @param id id
     * @return Spu
     */
    @Override
    public Spu selectSpuById(int id) {
        Spu spu = baseMapper.selectById(id);
        if(Optional.ofNullable(spu).isPresent()){
            return spu;
        }
        throw new NotFoundRecordException("轮播图不存在");
    }

    /**
     * 分页查找SPU
     * @param pageDTO 查询信息
     * @return List<Spu>
     */
    @Override
    public Page<SpuVO> selectSpuPage(PageDTO pageDTO) {
        /*Page<Spu> spu = new Page<>();
        spu.setSize(pageDTO.getSize());
        spu.setCurrent(pageDTO.getPage());
        return baseMapper.selectPage(spu, null);*/
        long offset = (pageDTO.getPage() - 1) * pageDTO.getSize();
        List<SpuVO> spuVO = baseMapper.selectSpuVOList(offset, pageDTO.getSize());
        Page<SpuVO> spuVOPage = new Page<>();
        return spuVOPage.setRecords(spuVO)
                .setSize(pageDTO.getSize())
                .setCurrent(pageDTO.getPage())
                .setTotal(baseMapper.getTotalCount());
    }

    /**
     * 更新SPU
     * @param update 更新信息
     * @return int
     */
    @Override
    public int updateSpu(SpuUpdate update) {
        Spu convert = spuConverter.convert(update);
        return baseMapper.updateById(convert);
    }

    /**
     * 通过id删除SPU
     * @param id id
     * @return int
     */
    @Override
    public int delSpu(int id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 获取所有spu
     * @return List<SpuVO>
     */
    @Override
    public List<SpuVO> getAll() {
        return baseMapper.selectSpuVOAll();
    }
}




