package com.wwdy.admin.converter;

import com.wwdy.admin.pojo.Spu;
import com.wwdy.admin.pojo.update.SpuUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * @author wwdy
 * @date 2022/3/22 15:31
 */
@Mapper(componentModel = "spring")
public interface SpuConverter extends Converter<SpuUpdate, Spu> {

    /**
     * SpuUpdate to Spu
     * @param source SpuUpdate
     * @return Spu
     */
    @Mappings({
            @Mapping(target = "deleted",ignore = true),
            @Mapping(target = "createdTime",ignore = true),
            @Mapping(target = "updatedTime",ignore = true)
    })
    @Override
    Spu convert(@Nullable SpuUpdate source);
}
