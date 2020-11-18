package com.cesar.mybatisplushigh.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cesar.mybatisplushigh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserMapper
 * @Description: TODO
 * @Author movit
 * @Date 2019/9/29
 * @Version V1.0
 **/
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from t_user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    List<User> selectMyList(Map<String, Object> map);

    IPage<User> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    IPage<User> selectUserPage2(IPage<User> page, @Param("param") User param);

    IPage<User> selectUserPage3(IPage<User> page, @Param("param") Map<String, Object> param);

}
