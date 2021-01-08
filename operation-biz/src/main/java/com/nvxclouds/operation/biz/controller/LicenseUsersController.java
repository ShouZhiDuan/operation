package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.dto.AuthDTO;
import com.nvxclouds.operation.biz.dto.DoBlackDTO;
import com.nvxclouds.operation.biz.service.MedusaLicenseUsersService;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseChildAccountQueryVO;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseListQueryVO;
import com.nvxclouds.operation.biz.vo.licence.IndividualListQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 11:40
 * @Description: 研究者相关服务API
 */
@RestController
@RequestMapping("/v1/researcher")
public class LicenseUsersController {

    @Autowired
    private MedusaLicenseUsersService usersService;

    @Log(name = "个人研究者列表数据查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryIndividualResearchers")
    //@Permission(name = "individualResearcher:list")
    public Object queryIndividualResearchers(IndividualListQueryVO query) {
        return BaseResult.ok(usersService.queryIndividualResearchers(query));
    }

    @Log(name = "个人研究者、企业研究者认证信息查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryIndividualEnterpriseResearchersAuthInfo/{id}/{type}")
    public Object queryIndividualResearchersAuthInfo(@PathVariable("id") Long id, @PathVariable("type") Integer type) {
        //type 1个人研究者  2企业研究者
        return BaseResult.ok(usersService.queryIndividualResearchersAuthInfo(id,type));
    }

    @Log(name = "个人、企业研究者认证操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/individualEnterpriseResearchersAuth")
    //@Permission(name = "researcher:auth")
    public Object individualEnterpriseResearchersAuth(@Valid @RequestBody AuthDTO authDTO) {
        usersService.individualResearchersAuth(authDTO);
        return BaseResult.ok();
    }

    @Log(name = "个人、企业研究者加入黑名单操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/individualEnterpriseResearchersBlack")
    public Object individualEnterpriseResearchersBlack(@Valid @RequestBody DoBlackDTO doBlackDTO) throws IOException {
        usersService.individualEnterpriseResearchersBlack(doBlackDTO);
        return BaseResult.ok();
    }


    @Log(name = "企业研究者列表数据查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryEnterpriseResearchers")
    //@Permission(name = "enterpriseResearcher:list")
    public Object queryEnterpriseResearchers(EnterpriseListQueryVO query) {
        return BaseResult.ok(usersService.queryEnterpriseResearchers(query));
    }


    @Log(name = "企业研究者子账户列表数据查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryEnterpriseChildAccounts")
    //@Permission(name = "researcher:queryEnterpriseChildAccounts")
    public Object queryEnterpriseChildAccounts(EnterpriseChildAccountQueryVO query) {
        //id 企业研究者ID
        return BaseResult.ok(usersService.queryEnterpriseChildAccounts(query));
    }


}
