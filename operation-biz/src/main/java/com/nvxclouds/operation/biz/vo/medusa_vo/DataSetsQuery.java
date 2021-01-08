package com.nvxclouds.operation.biz.vo.medusa_vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/28 11:08
 * @Description:数据集查询
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSetsQuery extends BaseQuery {
    /**
     * 数据集状态：
     * 已删除(DELETED)
     * 已注册，但是长时间未响应(OFFLINE)
     * 已注册，可以使用(ALIVE)
     * 黑名单状态(BLOCKED)
     */
    private String status;
    //机构名称
    private String organization;
    //可见性
    private String visibility;
}
