package com.cesar.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cesar.core.dao.UserMapper;
import com.cesar.core.entity.User;
import com.cesar.core.service.UserService;
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
