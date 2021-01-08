package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.pgsql.ResearchMethod;
import com.nvxclouds.operation.biz.dto.ReaserchMethodListDTO;
import com.nvxclouds.operation.biz.dto.ResearchMethodAddDTO;
import com.nvxclouds.operation.biz.vo.medusa_vo.ResearchMethodQuery;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 14:13
 * @Description:研究方法服务
 */
public interface ResearchMethodService extends Service<ResearchMethod> {


    /**
     * 新增编辑研究方法
     */
    void addMethod(ResearchMethodAddDTO dto);

    /**
     * 分页查询研究方法列表
     * @param query
     * @return
     */
    Object methods(ResearchMethodQuery query);

    /**
     * 查询研究方法详情
     * @param id
     * @return
     */
    Object details(Long id);

    /**
     * 具体研究方法适用数据集列表分页查询
     */
    Object applyList(Long id, BaseQuery query);

    /**
     *  数据使用情况详情列表分页查询
     */
    Object setMethods(Long id, BaseQuery query);


    /**
     * 获取所有研究方法列表
     */
    List<ReaserchMethodListDTO> getMethodList(Integer type);
}
