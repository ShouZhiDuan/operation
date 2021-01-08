package com.nvxclouds.operation.biz.vo.licence;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 13:28
 * @Description: 个人研究者黑名单查询数据
 */
@Data
public class IndividualBlackListQueryVO extends BaseQuery {
    //用户名
    private String userName;
    //姓名
    private String realName;
}
