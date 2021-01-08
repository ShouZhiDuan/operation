package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 16:36
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResearchMethodAddDTO {
    //研究方法ID
    @NotNull(message = "研究方法ID不能为空")
    private Long id;
    //研究方法名称
    @NotBlank(message = "研究方法名称不能为空")
    private String  name;
    //英文标题
    private String title;
    //中文标题
    private String titleZh;
    //英文描述
    private String description;
    //中文描述
    private String descriptionZh;
    //1：个人研究者 2：企业研究者 3：全部
    private Integer type;
    //f取消激活 t激活 新增的时候默认未激活
    private Boolean blocked;
    //操作的 doType=1新增  2编辑
    @NotNull(message = "请输入操作类型")
    private Integer doType;
}
