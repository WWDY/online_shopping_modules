package com.wwdy.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwdy.admin.pojo.Spu;
import com.wwdy.admin.pojo.vo.SpuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/3/22 10:43
 */
@Mapper
public interface SpuMapper extends BaseMapper<Spu> {

    /**
     * 多表联合查询spu
     *
     * @param offset 偏移量
     * @param size   页面大小
     * @return List<SpuVO>
     */
    List<SpuVO> selectSpuVOList(@Param("offset") long offset, @Param("size") int size);

    /**
     * 获取所有spu信息
     * @return List<SpuVO>
     */
    List<SpuVO> selectSpuVOAll();

    /**
     * 获取全部数量
     * @return int
     */
    @Select("select count(id) from online_shopping.spu where deleted = 0")
    int getTotalCount();
}




