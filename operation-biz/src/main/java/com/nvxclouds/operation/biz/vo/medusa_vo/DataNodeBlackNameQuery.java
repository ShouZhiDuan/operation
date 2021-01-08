package com.nvxclouds.operation.biz.vo.medusa_vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 16:31
 * @Description:
 */
@Data
public class DataNodeBlackNameQuery extends BaseQuery {
    private String userName;
    private String  nodeName;
    private String  organizationName;
}
