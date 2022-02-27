package com.wwdy.auth.converter;

import com.wwdy.auth.pojo.UserDO;
import com.wwdy.auth.pojo.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;


/**
 * @author wwdy
 * @date 2022/2/24 17:45
 */
@Mapper(componentModel = "spring")
public interface UserConverter extends Converter<UserDTO, UserDO> {

    /**
     * 类型转换
     * @param userDTO userDTO
     * @return UserDO
     */
    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdTime", ignore = true),
            @Mapping(target = "updatedTime", ignore = true)
    })
    UserDO convert(@Nullable UserDTO userDTO);

    /**
     * 类型转换
     * @param userDO userDO
     * @return UserDTO
     */
    @Mapping(target = "code", ignore = true)
    UserDTO convert(@Nullable UserDO userDO);

 }
