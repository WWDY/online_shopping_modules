package com.wwdy.admin.converter;


import com.wwdy.admin.pojo.SliderShow;
import com.wwdy.admin.pojo.update.SliderShowUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;


/**
 * @author wwdy
 * @date 2022/3/19 17:34
 */
@Mapper(componentModel = "spring")
public interface SliderShowConverter extends Converter<SliderShowUpdate, SliderShow> {

    /**
     * converter SliderShowUpdate to SliderShow
     * @param source SliderShowUpdate
     * @return SliderShow
     */
    @Override
    @Mappings({
            @Mapping(target = "updatedTime" ,ignore = true),
            @Mapping(target = "createdTime" ,ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    SliderShow convert(@Nullable SliderShowUpdate source);
}
