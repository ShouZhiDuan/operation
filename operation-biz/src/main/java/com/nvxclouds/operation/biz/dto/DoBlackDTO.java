package com.nvxclouds.operation.biz.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 13:23
 * @Description: 研究者加入黑名单操作
 */
@Data
public class DoBlackDTO {
      //研究者ID
      @NotNull(message = "研究者ID不能为空")
      private  Long userId;
      //操作类型 1个人研究者 2企业研究者
      @NotNull(message = "操作类型不能为空")
      private  Integer type;
      @NotNull(message = "黑名单原因不能为空")
      private  String  cause;
}
