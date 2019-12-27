package com.cesar.core.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cesar.core.user.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> getAll();
}
