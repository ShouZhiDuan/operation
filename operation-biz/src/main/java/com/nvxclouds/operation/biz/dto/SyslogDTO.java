package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 13:24
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SyslogDTO {
    private  Long    id;
    private  String  createTime;
    private  String  comments;
    private  String  keyWord;
    private  String  ip;
}
