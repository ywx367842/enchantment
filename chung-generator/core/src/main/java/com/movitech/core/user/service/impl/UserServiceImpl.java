package com.movitech.core.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.movitech.core.user.mapper.UserMapper;
import com.movitech.core.user.entity.User;
import com.movitech.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author movit
 * @Date 2019/12/5
 * @Version V1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll() {
        List<User> users = userMapper.selectAll(new QueryWrapper<User>());
        return users;
    }
}
