package com.nvxclouds.operation.biz.validated;

import com.nvxclouds.operation.biz.annotation.SuspendType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 16:23
 * @Description:
 */
public class SuspendedTypeValidated implements ConstraintValidator<SuspendType,String> {

    private String[] type;

    @Override
    public void initialize(SuspendType constraintAnnotation) {
        //注解元数据
       type = constraintAnnotation.type();
    }

    /**
     *
     * @param requestType 当前请求传入的参数
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String requestType, ConstraintValidatorContext constraintValidatorContext) {
       List<String> typeList = Arrays.asList(type);
       if(!typeList.contains(requestType)){
           return false;
       }
        return true;
    }
}
