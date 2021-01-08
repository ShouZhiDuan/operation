package com.nvxclouds.operation.biz.vo.medusa_vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/28 11:08
 * @Description:数据提供方黑名单查询数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataNodesBlackNameListQuery extends BaseQuery {
    //用户名称
    public String userName;
    //节点名称
    public String nodeName;
    //所属机构
    public String organization;
    //type 1表示个人研究者黑名单 2表示企业研究者黑名单  3表示数据提供者黑名单
    public Integer type;
}
