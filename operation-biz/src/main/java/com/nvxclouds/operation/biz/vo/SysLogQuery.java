package com.nvxclouds.operation.biz.vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/28 11:08
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLogQuery extends BaseQuery {
    private String keyWord;
    private String ip;
}
