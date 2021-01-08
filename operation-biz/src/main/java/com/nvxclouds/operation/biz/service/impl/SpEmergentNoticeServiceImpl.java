package com.nvxclouds.operation.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.api.dto.SyncNoticeDTO;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.SpEmergentNotice;
import com.nvxclouds.operation.biz.dto.CommandsListDTO;
import com.nvxclouds.operation.biz.dto.NoticeDetailsDTO;
import com.nvxclouds.operation.biz.dto.NoticeListDTO;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.mapper.SpEmergentNoticeMapper;
import com.nvxclouds.operation.biz.service.SpEmergentNoticeService;
import com.nvxclouds.operation.biz.utils.DateUtil;
import com.nvxclouds.operation.biz.vo.NoticeQueryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:22
 * @Description: 紧急通知服务
 */
@Service
public class SpEmergentNoticeServiceImpl extends AbstractService<SpEmergentNotice> implements SpEmergentNoticeService {
    @Autowired
    private SpEmergentNoticeMapper spEmergentNoticeMapper;

    @Override//同步紧急通知
    public void syncNotice(SyncNoticeDTO dto) {
        SpEmergentNotice spEmergentNotice = new SpEmergentNotice();
        BeanUtils.copyProperties(dto,spEmergentNotice);
        spEmergentNotice.setIsFinish(1);//1未完成 2已完成
        spEmergentNotice.setSyncTime(new Timestamp(System.currentTimeMillis()));
        spEmergentNoticeMapper.insert(spEmergentNotice);
    }


    @Override//查询紧急通知分页列表
    public Object notices(NoticeQueryVO query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<NoticeListDTO> listDTOS = spEmergentNoticeMapper.notices(query);
        CommonPage<NoticeListDTO> listPage = CommonPage.restPage(listDTOS);
        //判断内容是否超过30个字，查过则进行截取
        if(!CollectionUtils.isEmpty(listPage.getList())){
            for (NoticeListDTO dto : listPage.getList()){
                if(StringUtils.isNotBlank(dto.getContent()) && (dto.getContent().length() > 30)){
                    dto.setContent(dto.getContent().substring(0,30) + "...");
                }
            }
        }
        return listPage;
    }

    @Override//完成紧急通知操作
    public void noticesFinish(Long id) {
        SpEmergentNotice notice = spEmergentNoticeMapper.selectByPrimaryKey(id);
        Optional.ofNullable(notice).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
        notice.setIsFinish(2); //1未完成 2已完成
        notice.setFinishTime(new Timestamp(System.currentTimeMillis()));
        spEmergentNoticeMapper.updateByPrimaryKeySelective(notice);
        //调用监管端服务，执行完成
        //TO DO 、、、、、、
    }

    @Override//紧急通知详情查询
    public Object details(Long id) {
        SpEmergentNotice notice = spEmergentNoticeMapper.selectByPrimaryKey(id);
        Optional.ofNullable(notice).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
        NoticeDetailsDTO detailsDTO = new NoticeDetailsDTO();
        //BeanUtils.copyProperties(notice,detailsDTO);
        detailsDTO.setId(notice.getId());
        detailsDTO.setTitle(notice.getTitle());
        detailsDTO.setContent(notice.getContent());
        detailsDTO.setPublishTime(DateUtil.format(notice.getPublishTime(),"yyyy-MM-dd HH:mm:ss"));
        detailsDTO.setLevel(notice.getLevel());
        detailsDTO.setSendType(notice.getSendType());
        detailsDTO.setIsFinish(notice.getIsFinish());
        return  detailsDTO;
    }
}
