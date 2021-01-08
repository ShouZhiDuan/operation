package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/17 16:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegainSuspendedDTO {
    Long dataNodeId;
    Long dataSetId;
    //type = 1表示恢复操作、2表示暂停操作
    @NotNull(message = "type不能为空")
    Integer type;
}
