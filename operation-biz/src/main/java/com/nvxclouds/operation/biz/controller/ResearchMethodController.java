package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.dto.ReaserchMethodListDTO;
import com.nvxclouds.operation.biz.dto.ResearchMethodAddDTO;
import com.nvxclouds.operation.biz.service.ResearchMethodService;
import com.nvxclouds.operation.biz.vo.medusa_vo.ResearchMethodQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 14:17
 * @Description:
 */
@RestController
@RequestMapping("/v1/method")
public class ResearchMethodController {

    @Autowired
    private ResearchMethodService researchMethodService;

    @Log(name = "研究方法新增编辑操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/addMethod")
    //@Permission(name = "method:addMethod")
    public Object addMethod(@RequestBody @Valid ResearchMethodAddDTO dto) {
        researchMethodService.addMethod(dto);
        return BaseResult.ok();
    }

    @Log(name = "研究方法数据分页查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/methods")
    //@Permission(name = "study")
    public Object methods(ResearchMethodQuery query) {
        Object object =  researchMethodService.methods(query);
        return BaseResult.ok(object);
    }

    @Log(name = "研究方法详情查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/details/{id}")
    public Object details(@PathVariable("id") Long id) {
        Object object =  researchMethodService.details(id);
        return BaseResult.ok(object);
    }

    @Log(name = "具体研究方法适用数据集列表分页查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/apply/{id}")
    //@Permission(name = "method:applyList")
    public Object applyList(@PathVariable("id") Long id, BaseQuery query) {
        Object object =  researchMethodService.applyList(id,query);
        return BaseResult.ok(object);
    }

    @Log(name = "数据使用情况详情列表分页查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/setMethods/{id}")
    //@Permission(name = "method:setMethods")
    public Object setMethods(@PathVariable("id") Long id, BaseQuery query) {
        //id=数据集ID
        Object object =  researchMethodService.setMethods(id,query);
        return BaseResult.ok(object);
    }

    @Log(name = "获取所有研究方法列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/getMethod/list/{type}")
    public Object getMethodList(@PathVariable("type") Integer type) {
        //type=1个人研究者方法列表  2企业研究者方法 3全部都支持的方法
        List<ReaserchMethodListDTO> object =  researchMethodService.getMethodList(type);
        return BaseResult.ok(object);
    }


}
