package com.nvxclouds.operation.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.constants.EnvironmentVariables;
import com.nvxclouds.operation.biz.domain.DataNodesSort;
import com.nvxclouds.operation.biz.domain.ProviderDataSort;
import com.nvxclouds.operation.biz.domain.licence.AuthenticateInfo;
import com.nvxclouds.operation.biz.domain.licence.ResearcherBlacklist;
import com.nvxclouds.operation.biz.domain.licence.Users;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.dto.license_dto.BlackNameDTO;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.enums.LicenseUserStatusEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.feign.FastDFSClient;
import com.nvxclouds.operation.biz.license_mapper.AuthenticateInfoMapper;
import com.nvxclouds.operation.biz.license_mapper.ResearcherBlacklistMapper;
import com.nvxclouds.operation.biz.license_mapper.UsersMapper;
import com.nvxclouds.operation.biz.mapper.ProviderDataSortMapper;
import com.nvxclouds.operation.biz.pg_mapper.DataNodesMapper;
import com.nvxclouds.operation.biz.service.MedusaLicenseUsersService;
import com.nvxclouds.operation.biz.utils.HttpClientUtils;
import com.nvxclouds.operation.biz.utils.UuidUtils;
import com.nvxclouds.operation.biz.vo.DataSortQuery;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseChildAccountQueryVO;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseListQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 11:30
 * @Description:
 */
@Slf4j
@Service
public class MedusaLicenseUsersServiceImpl extends AbstractService<Users> implements MedusaLicenseUsersService {

      @Autowired
      private UsersMapper licenseUsersMapper;

      @Autowired
      private FastDFSClient fastDFSClient;

      @Autowired
      private AuthenticateInfoMapper authenticateInfoMapper;

      @Autowired
      private ResearcherBlacklistMapper researcherBlacklistMapper;

      @Autowired
      private DataNodesMapper dataNodesMapper;

      @Autowired
      private HttpClientUtils httpClientUtils;

      @Autowired
      private EnvironmentVariables environmentVariables;

      @Autowired
      private ProviderDataSortMapper providerDataSortMapper;

      @Override//查询个人研究者列表
      public Object queryIndividualResearchers(BaseQuery query) {
            PageHelper.startPage(query.getPage(),query.getPerPage());
            CommonPage<IndividualResearchListDTO> listPage = CommonPage.restPage(licenseUsersMapper.queryIndividualResearchers(query));
            return listPage;
      }

      @Override//查询个人研究者认证信息
      public Object queryIndividualResearchersAuthInfo(Long id, Integer type) {
            //type 1个人研究者  2企业研究者
            AuthenticateInfo infoQuery = new AuthenticateInfo();
            infoQuery.setUsersId(id);
            AuthenticateInfo infoDetails = authenticateInfoMapper.selectOne(infoQuery);
            Optional.ofNullable(infoDetails).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
            //处理查询照片路劲url
            Map<String,Object> map = new HashMap<>();
            if(1 == type){
                  //个人研究者
                  map.put("idCardFrontUrl","");
                  map.put("idCardBackUrl","");
                  if(StringUtils.isNotBlank(infoDetails.getIdCardFrontUrl())){
                        String  idCardFrontUrl = fastDFSClient.getFileById(Long.valueOf(infoDetails.getIdCardFrontUrl())).getData().getFullPath();
                        map.put("idCardFrontUrl",idCardFrontUrl);
                  }
                  if(StringUtils.isNotBlank(infoDetails.getIdCardBackUrl())){
                        String  idCardBackUrl = fastDFSClient.getFileById(Long.valueOf(infoDetails.getIdCardBackUrl())).getData().getFullPath();
                        map.put("idCardBackUrl",idCardBackUrl);
                  }
            }
            if(2 == type){
                  //企业研究者
                  map.put("licenseUrl","");
                  if(StringUtils.isNotBlank(infoDetails.getLicenseUrl())){
                        String  licenseUrl = fastDFSClient.getFileById(Long.valueOf(infoDetails.getLicenseUrl())).getData().getFullPath();
                        map.put("licenseUrl",licenseUrl);
                  }
            }
            return map;
      }


      @Override//个人研究者或者企业研究者认证操作
      public void individualResearchersAuth(AuthDTO authDTO) {
            List<Integer> listStatus = Arrays.asList(-1,1);
            Users user = licenseUsersMapper.selectByPrimaryKey(authDTO.getUserId());
            Optional.ofNullable(user).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
            Integer status = authDTO.getStatus();
            if(!listStatus.contains(status)){
                 throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
            }
            //只有未认证的状态才能有认证的数据,或者认证失败也是可以再次进行认证操作
            if(LicenseUserStatusEnum.UNAUTHORIZED_FILE_UPLOADED.getStatus().equals(user.getStatus()) || LicenseUserStatusEnum.DENIED.getStatus().equals(user.getStatus())){
                  if(status.equals(-1)){
                        log.info("个人或者企业研究者执行认证失败操作");
                        user.setStatus(LicenseUserStatusEnum.DENIED.getStatus());
                  }
                  if(status.equals(1)){
                        log.info("个人或者企业研究者执行认证通过操作");
                        user.setStatus(LicenseUserStatusEnum.AUTHORIZED.getStatus());
                  }
                  licenseUsersMapper.updateByPrimaryKeySelective(user);
            }else{
                  throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
            }
      }


      @Override//个人研究者或者企业研究者黑名单操作
      @Transactional("db3TransactionManager")
      public void individualEnterpriseResearchersBlack(DoBlackDTO doBlackDTO) throws IOException {
//            Users user = licenseUsersMapper.selectByPrimaryKey(doBlackDTO.getUserId());
//            ResearcherBlacklist blacklist = new ResearcherBlacklist();
//            blacklist.setId(UuidUtils.getUUID());
//            blacklist.setUserId(user.getId());
//            blacklist.setCause(doBlackDTO.getCause());
//            blacklist.setCreated(new Timestamp(System.currentTimeMillis()));
//            //加入黑名单
//            researcherBlacklistMapper.insert(blacklist);
//            //修改用户状态为注销状态：TERMINATED
//            user.setStatus(LicenseUserStatusEnum.TERMINATED.getStatus());
//            licenseUsersMapper.updateByPrimaryKeySelective(user);

            /**
             * 以下重写研究者黑名单逻辑
             * 将研究者加入黑名单，修改为注销状态，并同步medusa，该研究者正在执行研究也停止，并告知数据挖掘者（短信推送）。
             */
            //1、查询当前研究者信息
            Users user = licenseUsersMapper.selectByPrimaryKey(doBlackDTO.getUserId());
            if(ObjectUtils.isEmpty(user)){
                  log.info("未找到当前研究者数据：" + JSON.toJSONString(doBlackDTO));
                  throw new ApiProviderException(ApiProviderExceptionEnum.STATUS_EX);
            }
            //2、查询当前研究者是否在黑名单中
            ResearcherBlacklist blacklist = new ResearcherBlacklist();
            blacklist.setUserId(user.getId());
            ResearcherBlacklist isBlacked = researcherBlacklistMapper.selectOne(blacklist);
            if(!ObjectUtils.isEmpty(isBlacked)){
                log.info("当前研究者：" + JSON.toJSONString(blacklist) + "已加入黑名单无法操作");
                throw new ApiProviderException(ApiProviderExceptionEnum.IS_BLACKED);
            }
            //3、如果没有加入黑名单执行黑明单操作
            BlackNameDTO blackNameDTO = new BlackNameDTO();
            blackNameDTO.setId(user.getId());
            blackNameDTO.setCause(doBlackDTO.getCause());
            blackNameDTO.setType(doBlackDTO.getType());
            //4、调用License黑名单服务
            httpClientUtils.sendHttpsSyncedNoHeader(environmentVariables.getResearcherBlacedUrl(), JSON.toJSONString(blackNameDTO));
      }


      @Override//企业研究者列表查询
      public Object queryEnterpriseResearchers(EnterpriseListQueryVO query) {
            PageHelper.startPage(query.getPage(),query.getPerPage());
            List<EnterpriseResearchListDTO> list = licenseUsersMapper.queryEnterpriseResearchers(query);
            CommonPage<EnterpriseResearchListDTO> listPage = CommonPage.restPage(list);
            return listPage;
      }

      @Override//企业研究者子账户列表查询
      public Object queryEnterpriseChildAccounts(EnterpriseChildAccountQueryVO query) {
            PageHelper.startPage(query.getPage(),query.getPerPage());
            List<EnterpriseChildAccountListDTO> listDTOS = licenseUsersMapper.queryEnterpriseChildAccounts(query);
            CommonPage<EnterpriseChildAccountListDTO> listPage = CommonPage.restPage(listDTOS);
            return listPage;
      }

      @Override//数据需求方排名查询
      public Object topXDataDemand(DataSortQuery query) {
            //1、查询所有数据需求方数据列表
            List<TopDTO> list = licenseUsersMapper.topXDataDemand(null);
            //2、providerDataSortMapper
            //2、判断当前数据库是否有数据存在
            List<ProviderDataSort> datas =  providerDataSortMapper.selectAll();
            if(CollectionUtils.isEmpty(datas)){
                  if(!CollectionUtils.isEmpty(list)){
                        List<ProviderDataSort> addLis = new ArrayList<>();
                        //如果为空就全部插入
                        for (TopDTO dto : list){
                              ProviderDataSort providerDataSort = new ProviderDataSort();
                              providerDataSort.setArea(dto.getArea());
                              providerDataSort.setDataCount(dto.getDataCount());
                              providerDataSort.setIndexNo(dto.getIndex());
                              //0排名不变 1排名上升 -1排名下降
                              providerDataSort.setIsChange(0);
                              addLis.add(providerDataSort);
                        }
                        //首次查询全部插入
                        providerDataSortMapper.insertList(addLis);
                  }
                  /**
                   * 开始执行查询操作
                   */
                  return providerDataSortMapper.getProviderDataSortList(query);
            }
            if(!CollectionUtils.isEmpty(list)){
                  //存在排名数据
                  for(TopDTO dto : list){
                        //查出当前数据，存在更新、不存在插入
                        ProviderDataSort providerDataSort = new ProviderDataSort();
                        providerDataSort.setArea(dto.getArea());
                        ProviderDataSort selectData = providerDataSortMapper.selectOne(providerDataSort);
                        if(null == selectData){
                              //如果不存在排名数据记录
                              providerDataSort.setIsChange(0);
                              providerDataSort.setIndexNo(dto.getIndex());
                              providerDataSort.setDataCount(dto.getDataCount());
                              providerDataSortMapper.insert(providerDataSort);
                        }else {
                              //如果存在排名数据记录
                              if(dto.getIndex().compareTo(selectData.getIndexNo()) == 0){
                                    //1、排名相等
                                    selectData.setDataCount(dto.getDataCount());
                                    selectData.setIndexNo(dto.getIndex());
                                    selectData.setIsChange(0);//设置排名不变
                                    providerDataSortMapper.updateByPrimaryKey(selectData);
                              }
                              if(dto.getIndex().compareTo(selectData.getIndexNo()) == -1){
                                    //2、排名下降
                                    selectData.setDataCount(dto.getDataCount());
                                    selectData.setIndexNo(dto.getIndex());
                                    selectData.setIsChange(-1);//设置排名下降
                                    providerDataSortMapper.updateByPrimaryKey(selectData);
                              }
                              if(dto.getIndex().compareTo(selectData.getIndexNo()) == 1){
                                    //3、排名上升
                                    selectData.setDataCount(dto.getDataCount());
                                    selectData.setIndexNo(dto.getIndex());
                                    selectData.setIsChange(1);//设置排名上升
                                    providerDataSortMapper.updateByPrimaryKey(selectData);
                              }
                        }
                  }
                  /**
                   * 开始执行查询
                   */
                  return providerDataSortMapper.getProviderDataSortList(query);
            }
            return new ArrayList<>();
      }


      @Override//分布图数据查询
      public List<DataMapDTO> dataMap() {
            List<DataMapDTO> listDataProvider = dataNodesMapper.dataMap();
            List<DataMapDTO> listDataDomand =  licenseUsersMapper.dataMap();
            if(!CollectionUtils.isEmpty(listDataProvider)){
                  for (DataMapDTO proDTO : listDataProvider) {
                        if(CollectionUtils.isEmpty(listDataDomand)){
                              return listDataProvider;
                        }else {
                              for (DataMapDTO demandDTO : listDataDomand){
                                     if(demandDTO.getArea().equals(proDTO.getArea())){
                                           proDTO.setDataDemandCount(demandDTO.getDataDemandCount());
                                     }
                              }
                              return listDataProvider;
                        }
                  }
            }
            if(!CollectionUtils.isEmpty(listDataDomand)){
                  for (DataMapDTO demandDTO : listDataDomand) {
                        if(CollectionUtils.isEmpty(listDataProvider)){
                              return listDataDomand;
                        }else {
                              for (DataMapDTO proDTO : listDataProvider){
                                    if(demandDTO.getArea().equals(proDTO.getArea())){
                                          demandDTO.setDataProviderCount(proDTO.getDataProviderCount());
                                    }
                              }
                              return listDataDomand;
                        }
                  }
            }
            return new ArrayList();
      }
}
