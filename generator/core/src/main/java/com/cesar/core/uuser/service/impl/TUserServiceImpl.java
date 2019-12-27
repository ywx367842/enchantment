package com.cesar.core.uuser.service.impl;

import com.cesar.core.uuser.entity.TUser;
import com.cesar.core.uuser.mapper.TUserMapper;
import com.cesar.core.uuser.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cesar
 * @since 2019-12-25
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
