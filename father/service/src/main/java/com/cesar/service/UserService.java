package com.cesar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cesar.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getAll();
}
