package com.nvxclouds.operation.biz.exception;

import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: zhengxing.hu
 * @Date: 2020/5/20 15:26
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiProviderException extends RuntimeException {
    private ApiProviderExceptionEnum exceptionEnum;
}
