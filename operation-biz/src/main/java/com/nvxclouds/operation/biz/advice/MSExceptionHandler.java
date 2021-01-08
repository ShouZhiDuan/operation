package com.nvxclouds.operation.biz.advice;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.advice.GlobalExceptionHandler;
import com.nvxclouds.operation.biz.enums.MSExceptionEnum;
import com.nvxclouds.operation.biz.exception.MSException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: zhengxing.hu
 * @Date: 2020/5/21 13:23
 * @Description:
 */
@RestControllerAdvice
public class MSExceptionHandler extends GlobalExceptionHandler {


    @ExceptionHandler({MSException.class})
    public BaseResult<Object> handleFeignException(MSException exception) {
        exception.printStackTrace();
        MSExceptionEnum msExceptionEnum = exception.getMsExceptionEnum();
        return BaseResult.builder()
                .msg(msExceptionEnum.getMsg())
                .code(msExceptionEnum.getCode())
                .build();
    }

}
