package com.nvxclouds.operation.biz.vo.medusa_vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/28 11:08
 * @Description:系统资源查询模型
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesQuery extends BaseQuery {
    private String nodeName;
}
