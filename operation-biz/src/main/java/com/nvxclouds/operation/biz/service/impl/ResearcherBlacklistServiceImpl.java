package com.nvxclouds.operation.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.constants.EnvironmentVariables;
import com.nvxclouds.operation.biz.domain.licence.ResearcherBlacklist;
import com.nvxclouds.operation.biz.domain.licence.Users;
import com.nvxclouds.operation.biz.dto.CancleBlackDTO;
import com.nvxclouds.operation.biz.dto.IndividualDetailsDTO;
import com.nvxclouds.operation.biz.dto.IndividualResearcherBlackListDTO;
import com.nvxclouds.operation.biz.dto.license_dto.CancelBlackNameDTO;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.enums.LicenseUserStatusEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.license_mapper.ResearcherBlacklistMapper;
import com.nvxclouds.operation.biz.service.MedusaLicenseUsersService;
import com.nvxclouds.operation.biz.service.ResearcherBlacklistService;
import com.nvxclouds.operation.biz.utils.HttpClientUtils;
import com.nvxclouds.operation.biz.utils.JsonUtils;
import com.nvxclouds.operation.biz.vo.licence.IndividualBlackListQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 13:13
 * @Description:
 */
@Slf4j
@Service
public class ResearcherBlacklistServiceImpl extends AbstractService<ResearcherBlacklist> implements ResearcherBlacklistService {
      @Autowired
      private ResearcherBlacklistMapper researcherBlacklistMapper;
      @Autowired
      private MedusaLicenseUsersService medusaLicenseUsersService;
      @Autowired
      private HttpClientUtils httpClientUtils;
      @Autowired
      private EnvironmentVariables environmentVariables;



    @Override//个人研究者黑名单列表
    public Object queryIndividualResearchersBlackList(BaseQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<IndividualResearcherBlackListDTO> listDTOS = researcherBlacklistMapper.queryIndividualResearchersBlackList(query);
        CommonPage<IndividualResearcherBlackListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }


    @Override//个人研究者黑名单详情
    public Object queryIndividualResearchersBlackDetails(Long id, Integer type) {
        //type 1个人 2企业
        Users user = medusaLicenseUsersService.selectByPK(id);
        Optional.ofNullable(user).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
        ResearcherBlacklist blacklist = new ResearcherBlacklist();
        blacklist.setUserId(id);
        ResearcherBlacklist black = researcherBlacklistMapper.selectOne(blacklist);
        IndividualDetailsDTO detailsDTO = new IndividualDetailsDTO();
        detailsDTO.setCause(black.getCause());
        BeanUtils.copyProperties(user,detailsDTO);
        return detailsDTO;
    }

    /**
     * 从黑名单列表移除，用户可恢复使用平台的权限。
     */
    @Override//个人、企业研究者黑名单取消操作
    @Transactional("db3TransactionManager")
    public void cancelResearchersBlack(CancleBlackDTO cancleBlackDTO) throws IOException {
//             if(cancleBlackDTO.getType() == 1 || cancleBlackDTO.getType() == 2){
////                 //个人研究者黑名单取消操作
////                 ResearcherBlacklist blacklist = new ResearcherBlacklist();
////                 blacklist.setUserId(cancleBlackDTO.getId());
////                 ResearcherBlacklist delBlack = researcherBlacklistMapper.selectOne(blacklist);
////                 //移除黑名单
////                 researcherBlacklistMapper.delete(blacklist);
////                 //查出用户数据修改状态
////                 Users user = medusaLicenseUsersService.selectByPK(cancleBlackDTO.getId());
////                 //更新用户状态
////                 //先判断用户是否处于注销状态、只有注销状态才能取消黑名单
////                 if(user.getStatus().equals(LicenseUserStatusEnum.TERMINATED.toString())){
////                     user.setStatus(LicenseUserStatusEnum.AUTHORIZED.getStatus());
////                     medusaLicenseUsersService.updateSelective(user);
////                 }else {
////                     log.info("当前研究者黑名单状态异常，不是TERMINATED的状态！");
////                     throw new ApiProviderException(ApiProviderExceptionEnum.STATUS_EX);
////                 }
////                 return;
////             }
////             throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
                 //1、查询当前研究者数据是否存在
                 Users  users = medusaLicenseUsersService.selectByPK(cancleBlackDTO.getId());
                 if(ObjectUtils.isEmpty(users)){
                     log.info("当前研究者数据不存在：" + JSON.toJSONString(cancleBlackDTO));
                     throw new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT);
                 }
                 //2、判断当前数据是否存在黑明单中
                 ResearcherBlacklist blacklist = new ResearcherBlacklist();
                 blacklist.setUserId(cancleBlackDTO.getId());
                 ResearcherBlacklist delBlack = researcherBlacklistMapper.selectOne(blacklist);
                 if(ObjectUtils.isEmpty(delBlack)){
                     log.info("当前研究者：" + JSON.toJSONString(users) + "不存在黑名单中，无法操作");
                     throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
                 }
                 //3、调用License取消黑名单服务,构建请求数据
                 CancelBlackNameDTO cancelBlackNameDTO = new CancelBlackNameDTO();
                 cancelBlackNameDTO.setId(users.getId());
                 httpClientUtils.sendHttpsSyncedNoHeader(environmentVariables.getResearcherCancelUrl(), JSON.toJSONString(cancelBlackNameDTO));
    }


    @Override//企业研究者黑名单列表查询
    public Object queryEnterpriseResearchersBlackList(IndividualBlackListQueryVO query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<IndividualResearcherBlackListDTO> listDTOS = researcherBlacklistMapper.queryEnterpriseResearchersBlackList(query);
        CommonPage<IndividualResearcherBlackListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }
}
