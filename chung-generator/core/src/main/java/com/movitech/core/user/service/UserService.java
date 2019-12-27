package com.movitech.core.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.movitech.core.user.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> getAll();
}
