package com.wwdy.auth.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author wwdy
 * @date 2022/2/21 15:16
 */
@Configuration
@MapperScan(basePackages = "com.wwdy.auth.mapper")
public class MybatisPlusConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject,"updatedTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updatedTime", LocalDateTime::now, LocalDateTime.class);
    }
}
