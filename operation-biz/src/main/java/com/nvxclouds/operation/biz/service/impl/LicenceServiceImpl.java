package com.nvxclouds.operation.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.licence.*;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.enums.LicenceTypeEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.license_mapper.ActivationCodeMapper;
import com.nvxclouds.operation.biz.license_mapper.LicencePackageMapper;
import com.nvxclouds.operation.biz.license_mapper.LicencePackageMethodsMapper;
import com.nvxclouds.operation.biz.license_mapper.LicencePackageOrderMapper;
import com.nvxclouds.operation.biz.service.LicenceService;
import com.nvxclouds.operation.biz.service.MedusaLicenseUsersService;
import com.nvxclouds.operation.biz.utils.StringUtils;
import com.nvxclouds.operation.biz.utils.UuidUtils;
import com.nvxclouds.operation.biz.vo.licence.LicenceOrderQueryVO;
import com.nvxclouds.operation.biz.vo.licence.LicencePackageDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 16:52
 * @Description:许可证模块相关服务
 */
@Slf4j
@Service
public class LicenceServiceImpl extends AbstractService<LicencePackage> implements LicenceService {

     @Autowired//许可证基本信息操作
     private LicencePackageMapper licencePackageMapper;

     @Autowired//许可证与研究方法关系操作
     private LicencePackageMethodsMapper licencePackageMethodsMapper;

     @Autowired//许可证订单操作
     private LicencePackageOrderMapper licencePackageOrderMapper;

     @Autowired//许可证激活码
     private ActivationCodeMapper activationCodeMapper;

     @Autowired
     private MedusaLicenseUsersService medusaLicenseUsersService;


     /**
      * 新增编辑许可证
      */
     @Override
     @Transactional("db3TransactionManager")
     public void addLicence(LicencePackageAddDTO dto) {
          if(null == dto.getId()){
              log.info("开始新增许可证=" + JSON.toJSONString(dto));
              //新增
              //查询当前新增的许可证名称是否已存在
               LicencePackage licencePackage = new LicencePackage();
               licencePackage.setName(dto.getName());
               List<LicencePackage> isLsit =  licencePackageMapper.select(licencePackage);
               //null == licencePackageMapper.selectOneByExample(licencePackage)
               if(!CollectionUtils.isEmpty(isLsit)){
                     throw new ApiProviderException(ApiProviderExceptionEnum.DATA_EXIT);
               }
               //赋值
               LicencePackage addPackage = new LicencePackage();
               addPackage.setId(UuidUtils.getUUID());
               addPackage.setName(dto.getName());
               // INDIVIDUAL：个人   ENTERPRISE：企业
               addPackage.setType(dto.getType());
               addPackage.setPrice(dto.getPrice());
               addPackage.setDescription(dto.getDescription());
               addPackage.setCreateTime(new Timestamp(System.currentTimeMillis()));
               // 1表示未上架   2表示已上架
               addPackage.setStatus(1);
               addPackage.setNumberOfPeople((null == dto.getType()||dto.getType().equals(LicenceTypeEnum.INDIVIDUAL.getType()) ? 1 : dto.getNumberOfPeople()));
               licencePackageMapper.insert(addPackage);
               //保存当前许可证与研究方法的关系
               String methodNames = dto.getMethodNames();
               if(methodNames.contains(",")){
                  List<String> stringList = Arrays.asList(methodNames.split("\\,"));
                  stringList.stream().forEach(methodName -> {
                       LicencePackageMethods licencePackageMethods = new LicencePackageMethods();
                       licencePackageMethods.setId(UuidUtils.getUUID());
                       licencePackageMethods.setLicencePackageId(addPackage.getId());
                       licencePackageMethods.setMethodName(methodName);
                       licencePackageMethodsMapper.insert(licencePackageMethods);
                  });
               }else {
                    //说明当前只有一个方法
                    LicencePackageMethods licencePackageMethods = new LicencePackageMethods();
                    licencePackageMethods.setId(UuidUtils.getUUID());
                    licencePackageMethods.setLicencePackageId(addPackage.getId());
                    licencePackageMethods.setMethodName(methodNames);
                    licencePackageMethodsMapper.insert(licencePackageMethods);
               }
          }else {
               log.info("开始编辑许可证=" + JSON.toJSONString(dto));
               //编辑
               LicencePackage editLicence = licencePackageMapper.selectByPrimaryKey(dto.getId());
               Optional.ofNullable(editLicence).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
               editLicence.setName(dto.getName());
               // INDIVIDUAL：个人   ENTERPRISE：企业
               editLicence.setType(dto.getType());
               editLicence.setPrice(dto.getPrice());
               editLicence.setDescription(dto.getDescription());
               editLicence.setNumberOfPeople((null == dto.getType()||dto.getType().equals(LicenceTypeEnum.INDIVIDUAL.getType()) ? 1 : dto.getNumberOfPeople()));
               licencePackageMapper.updateByPrimaryKey(editLicence);//编辑许可证基础信息
               //处理研究方法
               LicencePackageMethods licencePackageMethods = new LicencePackageMethods();
               licencePackageMethods.setLicencePackageId(editLicence.getId());
               List<LicencePackageMethods> licencePackageMethodsList = licencePackageMethodsMapper.select(licencePackageMethods);
               if(!CollectionUtils.isEmpty(licencePackageMethodsList)){
                    licencePackageMethodsList.stream().forEach(del -> {
                         licencePackageMethodsMapper.deleteByPrimaryKey(del.getId());
                    });
               }
               //删完原始数据后，保存新的数据
               List<String> stringList = Arrays.asList(dto.getMethodNames().split("\\,"));
               stringList.stream().forEach(methodName -> {
                    LicencePackageMethods licencePackageMethodsEdit = new LicencePackageMethods();
                    licencePackageMethodsEdit.setId(UuidUtils.getUUID());
                    licencePackageMethodsEdit.setLicencePackageId(editLicence.getId());
                    licencePackageMethodsEdit.setMethodName(methodName);
                    licencePackageMethodsMapper.insert(licencePackageMethodsEdit);
               });
          }
     }

     /**
      * 查询许可证信息
      */
     @Override
     public Object findLicence(String id) {
          LicencePackage licencePackage = licencePackageMapper.selectByPrimaryKey(id);
          Optional.ofNullable(licencePackage).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
          LicencePackageDetailsVO detailsVO = new LicencePackageDetailsVO();
          BeanUtils.copyProperties(licencePackage,detailsVO);
          //处理研究方法
          LicencePackageMethods licencePackageMethods = new LicencePackageMethods();
          licencePackageMethods.setLicencePackageId(licencePackage.getId());
          List<LicencePackageMethods> methods = licencePackageMethodsMapper.select(licencePackageMethods);
          if(!methods.isEmpty()){
               String methodNames = "";
               for (LicencePackageMethods method : methods) {
                    methodNames = methodNames + method.getMethodName() + ",";
               }
               detailsVO.setMethodNames(methodNames.substring(0,methodNames.length() - 1));
          }else {
               detailsVO.setMethodNames("");
          }
          return detailsVO;
     }


     /**
      * 查询许可证列表
      */
     @Override
     public Object listLicence(BaseQuery query) {
          PageHelper.startPage(query.getPage(),query.getPerPage());
          List<LicencePackageListDTO>  licencePackageListDTOS = licencePackageMapper.listLicence(query);
          CommonPage<LicencePackageListDTO> listPage = CommonPage.restPage(licencePackageListDTOS);
          List<LicencePackageListDTO> methodList = listPage.getList();
          if(CollectionUtils.isEmpty(methodList)){
               return listPage;
          }else {
               for (LicencePackageListDTO licencePackageListDTO : methodList) {
                    String licencePackageId = licencePackageListDTO.getId();
                    LicencePackageMethods licencePackageMethods = new LicencePackageMethods();
                    licencePackageMethods.setLicencePackageId(licencePackageId);
                    List<LicencePackageMethods> methods = licencePackageMethodsMapper.select(licencePackageMethods);
                    if(CollectionUtils.isEmpty(methods)){
                         licencePackageListDTO.setMethodNames("");
                         continue;
                    }else {
                          String methodNames = "";
;                         for (LicencePackageMethods method : methods) {
                                     methodNames = methodNames + method.getMethodName() + ",";
                          }
                         licencePackageListDTO.setMethodNames(methodNames.substring(0,methodNames.length() - 1));
                    }

               }
               return listPage;
          }
     }

     /**
      * 许可证上架操作
      */
     @Override
     public void putOn(PutOnDTO dto) {
          // 1表示未上架   2表示已上架
          LicencePackage licencePackage = licencePackageMapper.selectByPrimaryKey(dto.getId());
          Optional.ofNullable(licencePackage).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
          //设置上下架状态  1表示未上架   2表示已上架
          licencePackage.setStatus(dto.getStatus());
          //设置上架时间
          if(2 == dto.getStatus()){
              licencePackage.setPutOnTime(new Timestamp(System.currentTimeMillis()));
          }
          licencePackageMapper.updateByPrimaryKey(licencePackage);
     }

    /**
     * 许可证订单列表
     */
    @Override
    public Object orderList(LicenceOrderQueryVO query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<LicenceOrderDTO> licenceOrderDTOS = licencePackageMapper.orderList(query);
        CommonPage<LicenceOrderDTO> listPage = CommonPage.restPage(licenceOrderDTOS);
        //处理研究方法
        List<LicenceOrderDTO> methodList = listPage.getList();
        if(CollectionUtils.isEmpty(methodList)){
             return listPage;
        }else {
            for (LicenceOrderDTO licenceOrderDTO : methodList) {
                String licencePackageId = licenceOrderDTO.getLicencePackageId();
                LicencePackageMethods licencePackageMethods = new LicencePackageMethods();
                licencePackageMethods.setLicencePackageId(licencePackageId);
                List<LicencePackageMethods> methods = licencePackageMethodsMapper.select(licencePackageMethods);
                if(CollectionUtils.isEmpty(methods)){
                    licenceOrderDTO.setMethodNames("");
                    continue;
                }else {
                    String methodNames = "";
                    for (LicencePackageMethods method : methods) {
                        methodNames = methodNames + method.getMethodName() + ",";
                    }
                    licenceOrderDTO.setMethodNames(methodNames.substring(0,methodNames.length() - 1));
                }
            }
        }
        //处理是否到期 1已支付 2未支付 3已激活 4已到期
        return listPage;
    }

    /**
     * 查询续费菜单列表
     */
    @Override
    public Object costMenus(String id) {
        LicencePackage licencePackage = licencePackageMapper.selectByPrimaryKey(id);
        Optional.ofNullable(licencePackage).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
        BigDecimal money = licencePackage.getPrice();
        List<Map<String,Object>> outList = new ArrayList<>();
        for (int a = 1; a <= 12; a++){
             Map<String,Object> map = new HashMap<>();
             map.put("month",a);
             map.put("money",new BigDecimal(a).multiply(money).setScale(2,BigDecimal.ROUND_HALF_UP));
             outList.add(map);
        }
        return outList;
    }

    /**
     * 许可证续费操作
     */
    @Override
    public void renew(RenewDTO dto) {
         String orderId = dto.getOrderId();
         LicencePackageOrder order = licencePackageOrderMapper.selectByPrimaryKey(orderId);
         Optional.ofNullable(order).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
         //续费月数
         Integer month = dto.getMonth();
         //查出单价
         LicencePackage licencePackage = new LicencePackage();
         licencePackage.setId(order.getLicencePackageId());
         LicencePackage priceLicence = licencePackageMapper.selectOne(licencePackage);
         order.setAmountMonth(month);
         order.setAmountMoney(new BigDecimal(month).multiply(priceLicence.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
         if(order.getStatus() == 1){
             //已续费，不能续费
             throw new ApiProviderException(ApiProviderExceptionEnum.REPETITION_PAY);
         }else {
             //修改支付状态 1已支付 2未支付 3已激活 4已到期
             order.setStatus(1);
         }
         licencePackageOrderMapper.updateByPrimaryKey(order);
         //查询当前订单用户
         Users users = medusaLicenseUsersService.selectByPK(order.getUserId());
         //保存完开始执行发送短信用户
         sendActivCodeToUser(users.getPhone());
    }

    @Transactional
    public void   sendActivCodeToUser(String phone){
        int codeCount = activationCodeMapper.selectCount(null);
        String activationCode = StringUtils.zeroFormat(codeCount + 1,6,false);
        //保存验证码
        ActivationCode code = new ActivationCode();
        code.setCode(activationCode);
        activationCodeMapper.insert(code);
        log.info(String.format("发送短信：%s，您的许可证激活码：%s"),phone,activationCode);
    }

}
