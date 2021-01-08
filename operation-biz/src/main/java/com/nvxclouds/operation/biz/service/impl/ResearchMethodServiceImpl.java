package com.nvxclouds.operation.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.pgsql.ResearchMethod;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.pg_mapper.ResearchMethodMapper;
import com.nvxclouds.operation.biz.service.ResearchMethodService;
import com.nvxclouds.operation.biz.vo.medusa_vo.ResearchMethodQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 14:13
 * @Description:研究方法服务
 */
@Service
public class ResearchMethodServiceImpl extends AbstractService<ResearchMethod> implements ResearchMethodService {
    private final static  List<Integer> TYPELIST = Arrays.asList(1,2,3);

    @Autowired
    private ResearchMethodMapper researchMethodMapper;

    @Override//新增编辑研究方法
    @Transactional("db2TransactionManager")
    public void addMethod(ResearchMethodAddDTO dto) {
            if(!TYPELIST.contains(dto.getType())){
                //非法type操作类型
                throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
            }
            //操作的 doType=1新增  2编辑
            if(1 == dto.getDoType()){
                ResearchMethod addResearchMethod = researchMethodMapper.selectByPrimaryKey(dto.getId());
                if(ObjectUtils.isNotEmpty(addResearchMethod)){
                    //当前数据已存在
                    throw new ApiProviderException(ApiProviderExceptionEnum.ID_EXIT);
                }
                 //新增
                ResearchMethod addMethod = new ResearchMethod();
                addMethod.setId(dto.getId());
                addMethod.setNameDB(dto.getName());
                addMethod.setName(dto.getTitle());
                addMethod.setNameZh(dto.getTitleZh());
                addMethod.setDescription(dto.getDescription());
                addMethod.setDescriptionZh(dto.getDescriptionZh());
                //1：个人研究者 2：企业研究者 3：全部
                addMethod.setType(dto.getType());
                addMethod.setBlocked(dto.getBlocked());
                addMethod.setCreatedTime(new Timestamp(System.currentTimeMillis()));
                //入库操作
                researchMethodMapper.insert(addMethod);
                return;
            }
            if(2 == dto.getDoType()){
                //查看当前数据是否存在
                ResearchMethod editResearchMethod = researchMethodMapper.selectByPrimaryKey(dto.getId());
                if(ObjectUtils.isEmpty(editResearchMethod)){
                    //当前数据不存在
                    throw new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT);
                }
                //编辑
                editResearchMethod.setNameDB(dto.getName());
                editResearchMethod.setName(dto.getTitle());
                editResearchMethod.setNameZh(dto.getTitleZh());
                editResearchMethod.setDescription(dto.getDescription());
                editResearchMethod.setDescriptionZh(dto.getDescriptionZh());
                //1：个人研究者 2：企业研究者 3：全部
                editResearchMethod.setType(dto.getType());
                editResearchMethod.setBlocked(dto.getBlocked());
                researchMethodMapper.updateByPrimaryKey(editResearchMethod);
                return;
            }
            throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
    }


    /**
     * 分页查询研究方法列表
     */
    @Override
    public Object methods(ResearchMethodQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<ResearchMethodListDTO> listDTOS = researchMethodMapper.methods(query);
        CommonPage<ResearchMethodListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }


    /**
     * 查询研究方法详情
     */
    @Override
    public Object details(Long id) {
        ResearchMethod method = researchMethodMapper.selectByPrimaryKey(id);
        Optional.ofNullable(method).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
        Map resultMap = new HashMap();
        resultMap.put("id",method.getId());
        resultMap.put("name",method.getNameDB());
        resultMap.put("title",method.getName());
        resultMap.put("titleZh",method.getNameZh());
        resultMap.put("description",method.getDescription());
        resultMap.put("descriptionZh",method.getDescriptionZh());
        resultMap.put("type",method.getType());
        resultMap.put("blocked",method.getBlocked());
        return resultMap;
    }


    /**
     * 具体研究方法适用数据集列表分页查询
     */
    @Override
    public Object applyList(Long id, BaseQuery query) {
        ResearchMethod method = researchMethodMapper.selectByPrimaryKey(id);
        PageHelper.startPage(query.getPage(),query.getPerPage());
        Optional.ofNullable(method).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
        List<ResearchMethodDataListDTO> listDTOS = researchMethodMapper.applyList(method.getNameDB());
        CommonPage<ResearchMethodDataListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }

    /**
     * 数据使用情况详情列表分页查询
     */
    @Override
    public Object setMethods(Long id, BaseQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<DataSetUsedListDTO>  listDTOS = researchMethodMapper.setMethods(id);
        CommonPage<DataSetUsedListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }

    /**
     * 获取所有研究方法列表
     */
    @Override
    public List<ReaserchMethodListDTO> getMethodList(Integer type) {
        //查出所有的blocked=true的方法 type 1个人研究者方法 2企业研究者方法 3全部支持的方法
        return researchMethodMapper.getMethodListByType(type);
    }
}
