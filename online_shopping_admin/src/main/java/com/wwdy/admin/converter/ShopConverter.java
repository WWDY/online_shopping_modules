package com.wwdy.admin.converter;

import com.wwdy.admin.pojo.Shop;
import com.wwdy.admin.pojo.update.ShopUpdate;
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
public interface ShopConverter extends Converter<ShopUpdate, Shop> {

    /**
     * ShopUpdate to Shop
     * @param source SpuUpdate
     * @return Spu
     */
    @Mappings({
            @Mapping(target = "deleted",ignore = true),
            @Mapping(target = "createdTime",ignore = true),
            @Mapping(target = "updatedTime",ignore = true),
            @Mapping(target = "sliderShows",ignore = true),
            @Mapping(target = "sales",ignore = true),
    })
    @Override
    Shop convert(@Nullable ShopUpdate source);
}
