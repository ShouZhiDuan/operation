package com.nvxclouds.operation.biz.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/10 19:46
 * @Description: 数据需求方排名数据
 */
@Data
public class ProviderDataSort {
    @Id
    @Column(name = "id" )
    private Long id;
    //当前地区数据节点总数
    @Column(name = "data_count" )
    private Long dataCount;
    //当前地区排名
    @Column(name = "index_no" )
    private Long indexNo;
    //当前地区排名是否发生变化  0排名不变 1排名上升 -1排名下降
    @Column(name = "is_change" )
    private Integer isChange;
    //当前地区名称
    @Column(name = "area" )
    private String area;
}
