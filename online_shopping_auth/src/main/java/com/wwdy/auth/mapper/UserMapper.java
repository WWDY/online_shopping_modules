package com.wwdy.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwdy.auth.pojo.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @author wwdy
 * @date 2022/2/21 14:48
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {
}
