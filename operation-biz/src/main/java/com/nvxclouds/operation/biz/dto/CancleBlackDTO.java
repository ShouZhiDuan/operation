package com.nvxclouds.operation.biz.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 14:25
 * @Description: 取消黑名单操作数据
 */
@Data
public class CancleBlackDTO {
      //研究者id
      @NotNull(message = "研究者ID不能为空")
      private Long id;
      //1个人研究者 2企业研究者黑名单取消操作
      @NotNull(message = "操作类型不能为空")
      private Integer type;
}
