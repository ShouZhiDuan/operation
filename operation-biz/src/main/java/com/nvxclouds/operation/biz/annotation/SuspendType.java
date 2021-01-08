package com.nvxclouds.operation.biz.annotation;

import com.nvxclouds.operation.biz.validated.SuspendedTypeValidated;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 16:21
 * @Description: 暂停、恢复
 */


@Constraint(validatedBy = { SuspendedTypeValidated.class })
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface SuspendType {

     String message() default "非法操作类型";

     String[] type();//SUSPENDED、ALIVE

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};

}
