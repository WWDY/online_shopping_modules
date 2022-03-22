package com.wwdy.admin.annotation.valid;

import com.wwdy.admin.validator.SpuValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author wwdy
 * @date 2022/3/22 11:01
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SpuValidator.class})
public @interface SpuStatus {

    //如果校验不通过返回的提示信息
    String message() default "状态值不合法";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
