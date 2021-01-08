package com.nvxclouds.operation.biz.vo.medusa_vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/07/21 16:50
 * @Description:数据历史使用查询数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsedCountQuery extends BaseQuery {
    //组织名称
    public String organization;
    //地区名称
    public String area;
    //数据集名称
    public String dataSetName;
}
