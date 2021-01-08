package com.nvxclouds.operation.biz.utils;

import java.util.UUID;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 17:48
 * @Description:
 */
public class UuidUtils {

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
