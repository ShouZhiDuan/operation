package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/31 11:36
 * @Description: 系统结构面板排名数据
 */
@Data
public class TopDTO {
    private  String  area;
    private  Long    dataCount;
    //当前排名数值
    private  Long    index;
    //0排名不变 1排名上升 -1排名下降
    private  Integer isChange = 0;
}
