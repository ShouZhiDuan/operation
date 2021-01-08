package com.nvxclouds.operation.biz.dto;

import com.nvxclouds.operation.biz.annotation.SuspendType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 16:12
 * @Description: 数据节点暂停恢复操作
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodesSuspendedDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    @SuspendType(type = {"SUSPENDED","ALIVE"})
    private String status;
}
