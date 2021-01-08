package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 13:40
 * @Description: 研究方法列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaserchMethodListDTO {
    private  Long    id;
    private  String  methodName;
    private  Integer type;
}
