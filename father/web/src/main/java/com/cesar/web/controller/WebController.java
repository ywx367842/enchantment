package com.cesar.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cesar.entity.User;
import com.cesar.service.UserService;
import com.cesar.web.framework.response.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName WebTest
 * @Description: TODO
 * @Author movit
 * @Date 2019/12/2
 * @Version V1.0
 **/

//@Api(tags = {"用户测试"})
@Api(value = "用户测试", description = "用户测试")
@RestController
@RequestMapping("user")
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("showAll")
    public Response showAll() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list = userService.getAll();
        return Response.succeed(list);
    }
}
