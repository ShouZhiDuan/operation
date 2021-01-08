package com.nvxclouds.operation.biz.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 19:12
 * @Description:续费数据
 */
@Data
public class RenewDTO {
    //订单ID
    @NotBlank(message = "许可证订单ID不能为空")
    private String orderId;
    //续费几个月
    @NotNull(message = "续费月份不能为空")
    private Integer month;
    //续费金额
    private BigDecimal money;
}
