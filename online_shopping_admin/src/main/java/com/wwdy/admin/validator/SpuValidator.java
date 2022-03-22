package com.wwdy.admin.validator;

import cn.hutool.core.util.StrUtil;
import com.wwdy.admin.annotation.valid.SpuStatus;
import com.wwdy.admin.enums.SpuEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author wwdy
 * @date 2022/3/22 11:04
 */
public class SpuValidator implements ConstraintValidator<SpuStatus,String> {


    @Override
    public void initialize(SpuStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isEmpty(value)) {
            return false;
        }
        return StrUtil.equals(value, SpuEnum.NO_SHELVES.getStatus()) || StrUtil.equals(value, SpuEnum.SHELVES.getStatus());
    }
}
