package com.nvxclouds.operation.biz.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 13:54
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuery {
     public Integer page = 1; //查询第几页
     public Integer perPage = 20; //每页显示几条
     public String startTime; //搜索查询起始时间
     public String endTime; //搜索查询截止时间
}
