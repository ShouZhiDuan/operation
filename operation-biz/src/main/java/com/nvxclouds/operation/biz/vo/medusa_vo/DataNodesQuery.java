package com.nvxclouds.operation.biz.vo.medusa_vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/28 11:08
 * @Description:数据节点查询
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataNodesQuery extends BaseQuery {
    /**
     * 节点状态：
     * 1未注册（NOT COLLECTED_）
     * 2未审批（INACTIVATED_）
     * 3在线（审批->ALIVE）
     * 4冻结（黑明单操作-BLOCKED ）
     * 5暂停（SUSPENDED）
     * 6离线（下线）：OFFLINE
     */
    public String status;
    //组织名称
    public String organization;
    //地区名称
    public String area;
}
