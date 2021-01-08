package com.nvxclouds.operation.biz.vo.medusa_vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/07/22 16:29
 * @Description:研究方法查询数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResearchMethodQuery extends BaseQuery {
    public String name;//方法名称
    public Integer type;//适用类型 1：个人研究者 2：企业研究者 3：全部
}
