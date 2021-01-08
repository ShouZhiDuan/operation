package com.nvxclouds.operation.biz.vo;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 14:10
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandsQueryVO extends BaseQuery {
    //1已响应 2未响应
    private Integer status;
}
