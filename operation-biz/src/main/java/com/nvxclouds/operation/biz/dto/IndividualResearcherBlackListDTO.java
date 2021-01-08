package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 13:39
 * @Description: 个人研究者黑名单列表数据
 */
@Data
public class IndividualResearcherBlackListDTO {
    private  Long  id;
    private  String  createdTime;
    private  String  userName;
    private  String  realName;
    private  String  companyName;
    private  String  area;
}
