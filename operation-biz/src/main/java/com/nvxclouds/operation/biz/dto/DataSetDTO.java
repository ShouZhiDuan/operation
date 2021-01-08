package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 15:04
 * @Description: 数据集列表模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetDTO {

    private String id;
    private String dataSetName;
    private String fileName;
    private String createTime;
    private String parentNode;
    private String organization;
    private String methods;//多个逗号隔开
    /**
     * 已删除(DELETED)
     * 已注册，但是长时间未响应(OFFLINE)
     * 已注册，可以使用(ALIVE)
     * 黑名单状态(BLOCKED)
     */
    private String status;


}
