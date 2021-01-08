package com.nvxclouds.operation.biz.vo.licence;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 11:50
 * @Description: 个人研究者列表查询数据
 */
@Data
public class IndividualListQueryVO extends BaseQuery {
    //用户名
    private String userName;
    //姓名
    private String realName;
    //所属单位名称
    private String companyName;
}
