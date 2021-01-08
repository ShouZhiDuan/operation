package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/31 10:54
 * @Description: 正在运行的研究数据
 */
@Data
public class RunningStudyListDTO {
      private String name;
      private String method;
      private String userName;
      private String createdTime;
      private String description;
      private String visibility;//0公开 1隐私
}
