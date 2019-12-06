package com.cesar.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cesar.core.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> getAll();
}
