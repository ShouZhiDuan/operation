package com.nvxclouds.operation.biz.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 13:23
 * @Description: 研究者认证数据
 */
@Data
public class AuthDTO {
      //研究者ID
      @NotNull(message = "研究者ID不能为空")
      private Long userId;
      //认证状态 -1认证失败操作 1认证通过操作
      @NotNull(message = "操作结果不能为空")
      private Integer status;
      //操作类型 1个人操作 2企业操作
      @NotNull(message = "操作类型不能为空")
      private Integer type;
}
