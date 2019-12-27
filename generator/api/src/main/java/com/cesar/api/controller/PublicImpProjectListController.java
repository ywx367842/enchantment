package com.cesar.api.controller;

import com.cesar.api.framework.response.Response;
import com.cesar.core.test.entity.PublicImpProjectList;
import com.cesar.core.test.service.PublicImpProjectListService;
import com.cesar.core.user.entity.User;
import com.cesar.core.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "公用-重点项目选择", description = "公用-重点项目选择")
@RequestMapping(value = "/public/public/")
public class PublicImpProjectListController {

    @Autowired
    private UserService userService;

    @GetMapping("showAll")
    public Response showAll() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list = userService.getAll();
        return Response.succeed(list);
    }

    @Autowired
    private PublicImpProjectListService publicImpProjectListService;

    /**
     * 公用-重点项目选择
     * Created by Cesar.Yuan
     */
    @ApiOperation(value = "公用-重点项目选择", notes = "公用-重点项目选择")
    @RequestMapping(value = {"getPublicImpProjectList"}, method = {RequestMethod.GET})
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "monthCode", required = false, dataType = "string", paramType = "query", value = "月编码"),
            @ApiImplicitParam(name = "areaCode", required = false, dataType = "string", paramType = "query", value = "城市公司编码"),
            @ApiImplicitParam(name = "yearCode", required = false, dataType = "string", paramType = "query", value = "年编码"),
    })*/
    public Response getPublicImpProjectList(@RequestParam String yearCode, @RequestParam String monthCode, @RequestParam String areaCode) {

        List<PublicImpProjectList> publicImpProjectList = publicImpProjectListService.getPublicImpProjectList(yearCode, monthCode, areaCode);

//        String say = publicImpProjectListService.say();
        return Response.succeed(publicImpProjectList);
    }
}
