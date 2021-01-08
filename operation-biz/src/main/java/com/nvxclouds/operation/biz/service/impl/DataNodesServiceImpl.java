package com.nvxclouds.operation.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.constants.EnvironmentVariables;
import com.nvxclouds.operation.biz.domain.DataNodesSort;
import com.nvxclouds.operation.biz.domain.licence.Users;
import com.nvxclouds.operation.biz.domain.pgsql.BlackName;
import com.nvxclouds.operation.biz.domain.pgsql.DataNodes;
import com.nvxclouds.operation.biz.domain.pgsql.DataSets;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.dto.medusa_dto.BlackNameDTO;
import com.nvxclouds.operation.biz.dto.medusa_dto.BlackNameRegainDTO;
import com.nvxclouds.operation.biz.dto.medusa_dto.BlackNameSuspendedDTO;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.enums.DataNodeStatus;
import com.nvxclouds.operation.biz.enums.DataSetStatus;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.mapper.DataNodesSortMapper;
import com.nvxclouds.operation.biz.pg_mapper.BlackNameMapper;
import com.nvxclouds.operation.biz.pg_mapper.DataNodesMapper;
import com.nvxclouds.operation.biz.pg_mapper.DataSetsMapper;
import com.nvxclouds.operation.biz.service.DataNodesService;
import com.nvxclouds.operation.biz.service.MedusaLicenseUsersService;
import com.nvxclouds.operation.biz.utils.DateUtil;
import com.nvxclouds.operation.biz.utils.HttpClientUtils;
import com.nvxclouds.operation.biz.vo.DataSortQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.*;
import com.nvxclouds.sync.api.dto.DataNode;
import jdk.nashorn.internal.runtime.Source;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 14:49
 * @Description:
 */
@Service
@Slf4j
public class DataNodesServiceImpl extends AbstractService<DataNodes> implements DataNodesService {

    @Autowired
    private DataNodesMapper dataNodesMapper;

    @Autowired
    private DataSetsMapper dataSetsMapper;

    @Autowired
    private BlackNameMapper blackNameMapper;

    @Autowired
    private MedusaLicenseUsersService licenseUsersService;

    @Autowired
    private EnvironmentVariables environmentVariables;

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private DataNodesSortMapper dataNodesSortMapper;


    @Override
    public Object list(DataNodesQuery query) throws ParseException {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<DataNodeDTO> list = dataNodesMapper.getDtataNodeList(query);
        CommonPage<DataNodeDTO> listPage = CommonPage.restPage(list);
        return listPage;
    }


    @Override
    public DataNodeDetailsDTO details(Long id) {
       //1、读取datanode详情数据
       DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(id);
       if(ObjectUtils.isEmpty(dataNodes)) {//当前数据节点不存在
           throw new ApiProviderException(ApiProviderExceptionEnum.NOT_EXIT_DATANODE);
       }
       //2、读取当前datanode的机构详情数据  TO DO、、、、、、
       //3、封装成一个对象返回给前端显示
        DataNodeDetailsDTO detailsDTO = new DataNodeDetailsDTO();
        detailsDTO.setDataNodeId(dataNodes.getId());
        detailsDTO.setNodeName(dataNodes.getName());
        detailsDTO.setOrganizationName("暂无数据");
        detailsDTO.setOrganizationCode("暂无数据");
        detailsDTO.setContactPerson("暂无数据");
        detailsDTO.setContactPhone("暂无数据");
        detailsDTO.setContactAdress("暂无数据");
        detailsDTO.setBusinessLicenseUrl("暂无数据");
        detailsDTO.setStatus(dataNodes.getStatus());
        return detailsDTO;
    }


    @Override
    public void suspended(DataNodesSuspendedDTO dto) throws IOException {
        //查出数据节点
        DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(dto.getId());
        //查出当前数据节点的用户
        Users usersLicense = new Users();
        usersLicense.setUsername(dataNodes.getUsername());
        Users phoneUser = licenseUsersService.selectOne(usersLicense);
        Optional.ofNullable(phoneUser).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.IS_NOT_EXSIT));
        if(DataNodeStatus.ALIVE.getStatus().equals(dto.getStatus())){
            //暂停操作
            BlackNameSuspendedDTO blackNameSuspendedDTO = new BlackNameSuspendedDTO();
            blackNameSuspendedDTO.setBlackNameType(3);
            blackNameSuspendedDTO.setPhone(phoneUser.getPhone());
            blackNameSuspendedDTO.setResourceId(dto.getId());
            //调用Medusa暂停服务getDataNodeSuspendUrl
            httpClientUtils.sendHttpsSyncedNoHeader(environmentVariables.getDataNodeSuspendUrl(),JSON.toJSONString(blackNameSuspendedDTO));
        }
        if(DataNodeStatus.SUSPENDED.getStatus().equals(dto.getStatus())){
            //恢复操作
            BlackNameRegainDTO blackNameRegainDTO = new BlackNameRegainDTO();
            blackNameRegainDTO.setBlackNameType(3);
            blackNameRegainDTO.setResourceId(dto.getId());
            //调用Medusa恢复服务getDataNodeRegainUrl
            httpClientUtils.sendHttpsSyncedNoHeader(environmentVariables.getDataNodeRegainUrl(),JSON.toJSONString(blackNameRegainDTO));
        }
    }


    @Override//数据节点黑名单操作
    @Transactional
    public void blacked(DataNodesBlackedDTO dto) throws IOException {
        //1、查询当前数据节点详情数据
        DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(dto.getId());
        dataNodes.setStatus(dto.getStatus());
        dataNodesMapper.updateByPrimaryKeySelective(dataNodes);
        //2、查看当前数据节点是否已加入黑名单，如果已加入则不能重复加黑名单操作
        BlackName blackName = new BlackName();
        blackName.setResourceId(dto.getId());
        blackName.setBlackNameType(3);
        BlackName isBlackName = blackNameMapper.selectOne(blackName);
        if(ObjectUtils.isNotEmpty(isBlackName)){ throw  new ApiProviderException(ApiProviderExceptionEnum.IS_BLACKED); }
        //3、如果没有加入黑名单则执行黑名单操作
        //查出当前数据节点的用户
        Users usersLicense = new Users();
        usersLicense.setUsername(dataNodes.getUsername());
        Users phoneUser = licenseUsersService.selectOne(usersLicense);
        Optional.ofNullable(phoneUser).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.IS_NOT_EXSIT));
        //4、构建黑名单新增数据
        BlackNameDTO blackNameDTO = new BlackNameDTO();
        blackNameDTO.setBlackNameType(3);
        blackNameDTO.setResourceId(dataNodes.getId());
        blackNameDTO.setCause(dto.getCause());
        blackNameDTO.setPhone(usersLicense.getPhone());
        //5、调用Medusa黑名单操作服务 getDataNodeBlockedUrl
        httpClientUtils.sendHttpsSyncedNoHeader(environmentVariables.getDataNodeBlockedUrl(),JSON.toJSONString(blackNameDTO));
    }


    @Override
    public Object repor() {
       //开始查询报表数据
       List<DataNodeReporDTO> listRepor = dataNodesMapper.repor();
       //返回出去
       List<DataNodeAreaDTO> outRepor = new ArrayList<>();
       if(!CollectionUtils.isEmpty(listRepor)){
           listRepor.forEach(dto -> {
               DataNodeAreaDTO data = new DataNodeAreaDTO();
               data.setAreaName(dto.getAreaName());
               if(CollectionUtils.isEmpty(dto.getList())){
                   outRepor.add(data);
               }else {
                   dto.getList().forEach(one -> {
                         if(one.getStatus().equals("ALIVE")){
                             data.setAlive(String.valueOf(one.getCount()));
                         }
                         if(one.getStatus().equals("NOT COLLECTED_")){
                             data.setNotCollected(String.valueOf(one.getCount()));
                         }
                         if(one.getStatus().equals("OFFLINE")){
                             data.setOffline(String.valueOf(one.getCount()));
                         }
                   });
                   outRepor.add(data);
               }
           });
       }
       return outRepor;
    }

    @Override
    public Object reporSet() {
        //开始查询报表数据
        List<DataNodeReporDTO> listRepor = dataSetsMapper.repor();
        //返回出去
        List<DataSetAreaDTO> outRepor = new ArrayList<>();
        if(!CollectionUtils.isEmpty(listRepor)){
            listRepor.forEach(dto -> {
                DataSetAreaDTO data = new DataSetAreaDTO();
                data.setAreaName(dto.getAreaName());
                if(CollectionUtils.isEmpty(dto.getList())){
                    outRepor.add(data);
                }else {
                    dto.getList().forEach(one -> {
                        if(one.getStatus().equals("ALIVE")){ //可用
                            data.setUsable(String.valueOf(one.getCount()));
                        }
                        if(one.getStatus().equals("OFFLINE")){ //不可用
                            data.setDisabled(String.valueOf(one.getCount()));
                        }
                    });
                    outRepor.add(data);
                }
            });
        }
        return outRepor;
    }

    @Override//查询数据集列表
    public Object setList(DataSetsQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<DataSetDTO> list = dataSetsMapper.getDataSetsList(query);
        CommonPage<DataSetDTO> listPage = CommonPage.restPage(list);
        return listPage;
    }

    @Override//系统资源列表查询
    public Object resources(ResourcesQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<ResourcesDTO> list = dataSetsMapper.resources(query.getNodeName());
        CommonPage<ResourcesDTO> listPage = CommonPage.restPage(list);
        //处理百分数
        if(!CollectionUtils.isEmpty(listPage.getList())){
            listPage.getList().forEach(dto -> {
                 String cpuUsage = dto.getCpuUsage();
                 String memUsage = dto.getMemUsage();
                 if(cpuUsage.contains(".")){
                     DecimalFormat df = new DecimalFormat("0.00");
                     Double do1 = Double.valueOf(cpuUsage)*100;
                     dto.setCpuUsage(df.format(do1) + "%");
                 }
                 if(memUsage.contains(".")){
                     DecimalFormat df = new DecimalFormat("0.00");
                     Double do1 = Double.valueOf(memUsage)*100;
                     dto.setMemUsage(df.format(do1) + "%");
                 }
            });
        }
        return listPage;
    }

    /**
     *  NOT COLLECTED 未注册
     *  OFFLINE       已注册、但长时间未响应
     *  INACTIVATED   未审批
     *  SUSPENDED     被暂停服务
     *  ALIVE         已注册，正常连接状态
     */
    @Override//数据节点审批操作->同步Medusa。
    @Transactional(value = "db2TransactionManager")
    public void approval(Long dataNodeId) {
          DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(dataNodeId);
          if(ObjectUtils.isEmpty(dataNodes)){
              throw  new ApiProviderException(ApiProviderExceptionEnum.NOT_EXIT_DATANODE);
          }
          //查看当前的数据节点状态是不是处于未审批的状态=INACTIVATED，否则不能执行审批。
        if(DataNodeStatus.INACTIVATED.getStatus().equals(dataNodes.getStatus())){
            //执行审批、将状态改成ALIVE
            dataNodes.setStatus(DataNodeStatus.ALIVE.getStatus());
            dataNodesMapper.updateByPrimaryKey(dataNodes);
        }else {
            throw  new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
        }
    }


    @Override//数据节点恢复、暂停操作
    @Transactional(value = "db2TransactionManager")
    public void regain(Long dataNodeId,Integer type) {
        log.info("暂停、恢复操作数据 = " + dataNodeId +  "," + "type=" + type);
        //type = 1表示恢复、2表示暂停
        DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(dataNodeId);
        log.info("DataNodes dataNodes =" + JSON.toJSONString(dataNodes));
        if(1 == type){
            log.info("================================dataNodes======================================进入1");
            //恢复操作
            if(ObjectUtils.isEmpty(dataNodes)){
                throw  new ApiProviderException(ApiProviderExceptionEnum.NOT_EXIT_DATANODE);
            }
            //查看当前结果是否是处于暂停状态=SUSPENDED，否则不能执行恢复操作。
            if(DataNodeStatus.SUSPENDED.getStatus().equals(dataNodes.getStatus())){
                //执行恢复、将状态改成ALIVE
                dataNodes.setStatus(DataNodeStatus.ALIVE.getStatus());
                dataNodesMapper.updateByPrimaryKey(dataNodes);
                /**
                 * =================================================
                 *  后续调用medusa恢复接口
                 * =================================================
                 */
                return;
            }else {
                throw  new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
            }
        }
        if(2 == type){
            log.info("================================dataNodes======================================进入2");
            //暂停：暂停数据节点，该节点的数据集也同时暂停使用。如有数据集正在参与研究，则研究也停止，并告知数据挖掘者（短信推送）。
            //查看当前结果是否是处于恢复状态=ALIVE，否则不能执行暂停操作。
            if(DataNodeStatus.ALIVE.getStatus().equals(dataNodes.getStatus())){
                //执行恢复、将状态改成ALIVE
                dataNodes.setStatus(DataNodeStatus.SUSPENDED.getStatus());
                dataNodesMapper.updateByPrimaryKey(dataNodes);
                /**
                 * =================================================
                 *  后续调用medusa暂停接口
                 * =================================================
                 */
                return;
            }else {
                throw  new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
            }
        }
        log.info("================================dataNodes======================================进入3");
        throw  new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
    }


    /**
     * 废弃
     */
    @Override//数据集恢复、暂停操作
    @Transactional(value = "db2TransactionManager")
    public void regainSet(Long dataSetId,Integer type) {
        //type = 1表示恢复、2表示暂停
        DataSets dataSets = dataSetsMapper.selectByPrimaryKey(dataSetId);
        log.info("DataSets dataSets = " + JSON.toJSONString(dataSets));
        if(1 == type){
            log.info("================================dataSets======================================进入1");
            //恢复操作
            if(ObjectUtils.isEmpty(dataSets)){
                throw  new ApiProviderException(ApiProviderExceptionEnum.NOT_EXIT_DATASET);
            }
            //查看当前结果是否是处于暂停状态=SUSPENDED，否则不能执行恢复操作。
            if(DataSetStatus.SUSPENDED.getStatus().equals(dataSets.getStatus())){
                //执行恢复、将状态改成ALIVE
                dataSets.setStatus(DataNodeStatus.ALIVE.getStatus());
                dataSetsMapper.updateByPrimaryKey(dataSets);
                /**
                 * =================================================
                 *  后续调用medusa恢复接口
                 * =================================================
                 */
                return;
            }else {
                throw  new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
            }
        }
        if(2 == type){
            log.info("===================================dataSets===================================进入2");
            //暂停：暂停数据集，修改状态为不可用，如该数据集正在参与研究，则研究也停止，并告知数据挖掘者（短信推送）。
            //暂停操作
            if(ObjectUtils.isEmpty(dataSets)){
                throw  new ApiProviderException(ApiProviderExceptionEnum.NOT_EXIT_DATASET);
            }
            //查看当前结果是否是处于暂停状态=ALIVE，否则不能执行暂停操作。
            if(DataSetStatus.ALIVE.getStatus().equals(dataSets.getStatus())){
                //执行恢复、将状态改成SUSPENDED
                dataSets.setStatus(DataNodeStatus.SUSPENDED.getStatus());
                dataSetsMapper.updateByPrimaryKey(dataSets);
                /**
                 * =================================================
                 *  后续调用medusa暂停接口
                 * =================================================
                 */
                return;
            }else {
                throw  new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
            }

        }
        log.info("====================================dataSets==================================进入3");
        throw  new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
    }

    @Override
    public Object dayMonth(Integer type, BaseQuery query) {
        //日月统计数据集使用列表 type=1日纬度2月纬度
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<DataSetDayMonthDTO> list = null;
        if(1 == type){
            //查询日统计
            list = dataSetsMapper.byDayCount(query);
        }
        if(2 == type){
            //查询月统计
           list = dataSetsMapper.byMonthCount(query);
        }
        CommonPage<DataSetDayMonthDTO> listPage = CommonPage.restPage(list);
        return listPage;
    }


    @Override//研究方法下的数据集统计
    public Object methodDataSetCount() {
        List<DataSetStudyMethodCount> list  =   dataSetsMapper.byStudyMethodCount();
        return CollectionUtils.isEmpty(list) ? new ArrayList<>() : list;
    }

    @Override//数据提供方黑名单列表查询
    public Object blackNameList(DataNodesBlackNameListQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        if(null == query.getType()){
            throw new ApiProviderException(ApiProviderExceptionEnum.TYPE_NOT_NULL);
        }
        List outList = new ArrayList();
        switch (query.getType()){
            case 1 ://个人研究者黑名单
                log.info("查询个人研究者黑名单列表");
                break;
            case 2 ://企业研究者黑名单
                log.info("查询企业研究者黑名单列表");
                break;
            case 3 ://数据提供者黑名单
                log.info("查询数据提供者黑名单列表");
                outList = dataNodesMapper.dataProviderList(query);
                break;
            default:
                throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
        }
        //处理分页模型
        CommonPage listPage = CommonPage.restPage(outList);
        return listPage;
    }


    @Override//黑名单详情查询
    public Object details(Integer type, Integer id) {
        switch (type){
            case 1 ://个人研究者黑名单详情
                return  null;
            case 2 ://企业研究者黑名单详情
                return  null;
            case 3 ://数据提供者黑名单详情
                DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(id);
                Optional.ofNullable(dataNodes).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
                DataNodeBlackNameDetailsDTO blackNameDetailsDTO = new DataNodeBlackNameDetailsDTO();
                blackNameDetailsDTO.setCreatedTime(DateUtil.format(dataNodes.getCreated(),"yyyy-MM-dd HH:mm:ss"));
                blackNameDetailsDTO.setUserName(dataNodes.getUsername());
                blackNameDetailsDTO.setNodeName(dataNodes.getName());
                blackNameDetailsDTO.setOrganization("暂无机构名称");
                blackNameDetailsDTO.setConcatName("暂无联系人姓名");
                blackNameDetailsDTO.setConcatPhone("暂无联系人电话");
                blackNameDetailsDTO.setConcatEmial("暂无联系邮箱");
                blackNameDetailsDTO.setArea(dataNodes.getArea());
                blackNameDetailsDTO.setConcatAddress("暂无联系地址");
                //查出黑名单原因blackNameMapper
                BlackName blackName = new BlackName();
                blackName.setResourceId(dataNodes.getId());
                blackName.setBlackNameType(type);
                blackName =  blackNameMapper.selectOne(blackName);
                Optional.ofNullable(blackName).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
                blackNameDetailsDTO.setBlackNameCause(blackName.getCause());
                return blackNameDetailsDTO;
            default:
                throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
        }
    }

    @Override//黑名单取消操作
    @Transactional(value = "db2TransactionManager")
    public Object cancleBlackName(Integer type, Integer id) {
        switch (type){
            case 1 ://个人研究者黑名取消操作
                return  null;
            case 2 ://企业研究者黑名单取消操作
                return  null;
            case 3 ://数据提供者黑名单取消操作
                DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(id);
                Optional.ofNullable(dataNodes).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
                BlackName blackName = new BlackName();
                blackName.setBlackNameType(type);
                blackName.setResourceId(dataNodes.getId());
                blackName = blackNameMapper.selectOne(blackName);
                //移除黑名单
                blackNameMapper.delete(blackName);
                //设置节点的status=  DataNodeStatus.ALIVE;
                dataNodes.setStatus(DataNodeStatus.ALIVE.getStatus());
                dataNodesMapper.updateByPrimaryKey(dataNodes);
                return null;
            default:
                throw new ApiProviderException(ApiProviderExceptionEnum.ILLEGAL_OPERRATION);
        }
    }


    @Override//数据集历史使用列表查看
    public Object usedCountList(UsedCountQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        //执行查询
        List<DataSetUsedDTO> list =  dataSetsMapper.usedCountList(query);
        CommonPage<DataSetUsedDTO> listPage = CommonPage.restPage(list);
        return listPage;
    }


    @Override//查询数据提供方黑名单列表
    public Object queryDataNodeBlackNameList(BaseQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<DataNodeBlackNameListDTO> listDTOS =  dataNodesMapper.queryDataNodeBlackNameList(query);
        CommonPage<DataNodeBlackNameListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }

    @Override//数据节点黑名单详情
    public Object queryDataNodeBlackNameDetails(Long id) {
        BlackName blackName = new BlackName();
        blackName.setResourceId(id);
        BlackName blackNameCause = blackNameMapper.selectOne(blackName);
        //查询数据节点
        DataNodes dataNodes = dataNodesMapper.selectByPrimaryKey(id);
        DataProviderBlackNameDetailsDTO detailsDTO = new DataProviderBlackNameDetailsDTO();
        //创建时间
        detailsDTO.setCreated(DateUtil.format(dataNodes.getCreated(),"yyyy-MM-dd HH:mm:ss"));
        //用户名
        detailsDTO.setUsername(dataNodes.getUsername());
        //节点名称
        detailsDTO.setNodeName(dataNodes.getName());
        //所属机构
        detailsDTO.setCompanyName("暂不支持");
        //联系人姓名
        detailsDTO.setRealName("暂不支持");
        //联系人电话
        detailsDTO.setPhone("暂不支持");
        //联系人邮箱
        detailsDTO.setEmail("暂不支持");
        //地区
        detailsDTO.setArea(dataNodes.getArea());
        //联系地址
        detailsDTO.setAddress("暂不支持");
        //加入黑名单原因
        detailsDTO.setCause(blackNameCause.getCause());
        return detailsDTO;
    }

    @Override//数据节点黑名单取消操作
    @Transactional("db2TransactionManager")
    public void cancelDataNodeBlack(CancleBlackDTO cancleBlackDTO) {
        //1、查出当前黑名单数据删除
        BlackName blackName = new BlackName();
        blackName.setResourceId(cancleBlackDTO.getId());
        blackNameMapper.delete(blackName);
        //2、查出当前数据节点详情
    }


    @Override//查询正在运行的研究列表
    public Object queryRunningStudies(BaseQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<RunningStudyListDTO> listDTOS =  dataNodesMapper.queryRunningStudies(query);
        CommonPage<RunningStudyListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }

    /**
     *  ---------------------------------改造---------------------------------------------
     */
    @Override//数据提供方排名查询
    public Object topXDataProvider(DataSortQuery query) {
        //1、获取所有的排名数据
        List<TopDTO> list = dataNodesMapper.topXDataProvider(null,null);
        //2、判断当前数据库是否有数据存在
        List<DataNodesSort> datas =  dataNodesSortMapper.selectAll();
        if(CollectionUtils.isEmpty(datas)){
            if(!CollectionUtils.isEmpty(list)){
                List<DataNodesSort> addLis = new ArrayList<>();
                //如果为空就全部插入
                for (TopDTO dto : list){
                    DataNodesSort nodesSort = new DataNodesSort();
                    nodesSort.setArea(dto.getArea());
                    nodesSort.setDataCount(dto.getDataCount());
                    nodesSort.setIndexNo(dto.getIndex());
                    //0排名不变 1排名上升 -1排名下降
                    nodesSort.setIsChange(0);
                    addLis.add(nodesSort);
                }
                //首次查询全部插入
                dataNodesSortMapper.insertList(addLis);
            }
            /**
             * 开始执行查询操作
             */
            return dataNodesSortMapper.getDataNodeSortList(query);
        }
        if(!CollectionUtils.isEmpty(list)){
            //存在排名数据
            for(TopDTO dto : list){
                //查出当前数据，存在更新、不存在插入
                DataNodesSort dataNodesSort = new DataNodesSort();
                dataNodesSort.setArea(dto.getArea());
                DataNodesSort selectData = dataNodesSortMapper.selectOne(dataNodesSort);
                if(null == selectData){
                    //如果不存在排名数据记录
                    dataNodesSort.setIsChange(0);
                    dataNodesSort.setIndexNo(dto.getIndex());
                    dataNodesSort.setDataCount(dto.getDataCount());
                    dataNodesSortMapper.insert(dataNodesSort);
                }else {
                    //如果存在排名数据记录
                    if(dto.getIndex().compareTo(selectData.getIndexNo()) == 0){
                        //1、排名相等
                        selectData.setDataCount(dto.getDataCount());
                        selectData.setIndexNo(dto.getIndex());
                        selectData.setIsChange(0);//设置排名不变
                        dataNodesSortMapper.updateByPrimaryKey(selectData);
                    }
                    if(dto.getIndex().compareTo(selectData.getIndexNo()) == -1){
                        //2、排名下降
                        selectData.setDataCount(dto.getDataCount());
                        selectData.setIndexNo(dto.getIndex());
                        selectData.setIsChange(-1);//设置排名下降
                        dataNodesSortMapper.updateByPrimaryKey(selectData);
                    }
                    if(dto.getIndex().compareTo(selectData.getIndexNo()) == 1){
                        //3、排名上升
                        selectData.setDataCount(dto.getDataCount());
                        selectData.setIndexNo(dto.getIndex());
                        selectData.setIsChange(1);//设置排名上升
                        dataNodesSortMapper.updateByPrimaryKey(selectData);
                    }
                }
            }
            /**
             * 开始执行查询
             */
            return dataNodesSortMapper.getDataNodeSortList(query);
        }
        return new ArrayList<>();
    }

    /**
     * 新数据集暂停、恢复操作
     * type = 1表示恢复操作、2表示暂停操作
     */
    @Override
    public void regainSetNew(RegainSuspendedDTO dto) throws IOException {
        //1、获取当前数据集ID
        Long dataSetId = dto.getDataSetId();
        //2、查询当前数据集详情
        DataSets set = dataSetsMapper.selectByPrimaryKey(dataSetId);
        if(ObjectUtils.isEmpty(set)){
            log.info("当前数据集：" + JSON.toJSONString(dto) + "不存在");
            throw  new ApiProviderException(ApiProviderExceptionEnum.NOT_EXIT_DATASET);
        }
        //3、查出当前数据挖掘者信息
        DataNodes dataNode = dataNodesMapper.selectByPrimaryKey(set.getDatanodeId());
        Users users = new Users();
        users.setUsername(dataNode.getUsername());
        Users phoneUser = licenseUsersService.selectOne(users);
        if(ObjectUtils.isEmpty(phoneUser)){
            log.info("当前数据节点：" + JSON.toJSONString(dataNode) + "没有找到相关的数据挖掘者");
            throw new ApiProviderException(ApiProviderExceptionEnum.IS_NOT_EXSIT);
        }
        licenseUsersService.selectByPK(1);
        if(1 == dto.getType()){//恢复操作
            //构建数据集恢复操作数据
            BlackNameRegainDTO regainDTO = new BlackNameRegainDTO();
            regainDTO.setResourceId(set.getId());//设置当前数据集ID
            regainDTO.setPhone(phoneUser.getPhone());//设置数据挖掘者手机号码
            httpClientUtils.sendHttpsSyncedNoHeader(environmentVariables.getDataSetRegainUrl(),JSON.toJSONString(regainDTO));
        }
        if(2 == dto.getType()){//暂停操作
            //构建数据集暂停操作数据
            BlackNameSuspendedDTO suspendedDTO = new BlackNameSuspendedDTO();
            suspendedDTO.setResourceId(set.getId());//设置当前数据集ID
            suspendedDTO.setPhone(phoneUser.getPhone());//设置数据挖掘者手机号码
            httpClientUtils.sendHttpsSyncedNoHeader(environmentVariables.getDataSetSuspendUrl(),JSON.toJSONString(suspendedDTO));
        }
    }


}



