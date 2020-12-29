package com.cesar.api.controller;

import com.cesar.api.framework.response.Response;
import com.cesar.core.entity.User;
import com.cesar.core.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Api(tags = {"用户测试"})
@Api(value = "用户测试", description = "用户测试")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("showAll")
    public Response showAll() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list = userService.getAll();
        return Response.succeed(list);
    }
}
