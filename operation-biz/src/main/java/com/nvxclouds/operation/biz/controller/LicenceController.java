package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.dto.LicencePackageAddDTO;
import com.nvxclouds.operation.biz.dto.PutOnDTO;
import com.nvxclouds.operation.biz.dto.RenewDTO;
import com.nvxclouds.operation.biz.service.LicenceService;
import com.nvxclouds.operation.biz.vo.licence.LicenceOrderQueryVO;
import com.nvxclouds.operation.biz.vo.licence.LicencePackageQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 13:25
 * @Description: 许可证模块Api
 */

@RestController
@RequestMapping("/v1/licence")
public class LicenceController {

    @Autowired
    private LicenceService licenceService;


    @Log(name = "新增、编辑许可证", biz = LogTypeEnum.OPERATION)
    @PostMapping("/addLicence")
    //@Permission(name = "license:addLicense")
    public Object addLicence(@Valid @RequestBody LicencePackageAddDTO dto) {
        licenceService.addLicence(dto);
        return BaseResult.ok();
    }


    @Log(name = "查询许可证详情", biz = LogTypeEnum.OPERATION)
    @GetMapping("/{id}")
    public Object findLicence(@PathVariable("id") String id) {
        return BaseResult.ok(licenceService.findLicence(id));
    }


    @Log(name = "查询许可证列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/listLicence")
    //@Permission(name = "license:listLicence")
    public Object listLicence(LicencePackageQueryVO query) {
        return BaseResult.ok(licenceService.listLicence(query));
    }


    @Log(name = "许可证上架、下架操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/putOn")
    public Object putOn(@Valid @RequestBody PutOnDTO dto) {
        // 1表示未上架   2表示已上架
        licenceService.putOn(dto);
        return BaseResult.ok();
    }


    @Log(name = "获取许可证订单列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/orderList")
    public Object orderList(LicenceOrderQueryVO vo) {
        return BaseResult.ok(licenceService.orderList(vo));
    }


    @Log(name = "获取续费菜单列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/costMenus/{id}")
    public Object costMenus(@PathVariable("id") String id) {
        //许可证ID
        return BaseResult.ok(licenceService.costMenus(id));
    }


    @Log(name = "许可证续费操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/renew")
    public Object renew(@Valid @RequestBody RenewDTO dto) {
        licenceService.renew(dto);
        return BaseResult.ok();
    }







}
