package com.wwdy.admin.converter;

import com.wwdy.admin.pojo.Category;
import com.wwdy.admin.pojo.update.CategoryUpdate;
import com.wwdy.admin.pojo.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author wwdy
 * @date 2022/3/25 13:08
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter extends Converter<CategoryUpdate, Category> {

    /**
     * CategoryUpdate to Category
     * @param source CategoryUpdate
     * @return Category
     */
    @Mappings({
            @Mapping(target = "deleted", ignore = true),
            @Mapping(target = "createdTime", ignore = true),
            @Mapping(target = "updatedTime", ignore = true)
    })
    @Override
    Category convert(@Nullable CategoryUpdate source);

    /**
     * CategoryVO to Category
     * @param category category
     * @return CategoryVO
     */
    @Mappings({
            @Mapping(target = "children", ignore = true),
            @Mapping(target = "value", source = "id")
    })
    CategoryVO convert(@Nullable Category category);

    /**
     * CategoryVO to Category
     * @param categories categories
     * @return List<CategoryVO>
     */
    List<CategoryVO> convert(@Nullable List<Category> categories);
}
