package com.cesar.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cesar.test.entity.Clause;
import com.cesar.test.mapper.ClauseMapper;
import com.cesar.test.service.IClauseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cesar
 * @since 2020-12-29
 */
@Service
public class ClauseServiceImpl extends ServiceImpl<ClauseMapper, Clause> implements IClauseService {

}
