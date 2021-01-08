package com.nvxclouds.operation.biz.exception;

import com.nvxclouds.operation.biz.enums.MSExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: zhengxing.hu
 * @Date: 2020/5/25 11:27
 * @Description:
 */
@AllArgsConstructor
@Getter
public class MSException extends RuntimeException {
    private MSExceptionEnum msExceptionEnum;
}
