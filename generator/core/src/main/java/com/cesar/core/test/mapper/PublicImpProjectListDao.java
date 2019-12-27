package com.cesar.core.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cesar.core.test.entity.PublicImpProjectList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
@Mapper
public interface PublicImpProjectListDao extends BaseMapper<PublicImpProjectList> {

    /**
     * 公用-重点项目选择
     * Created by Cesar.Yuan
     */
    List<PublicImpProjectList> getPublicImpProjectList(ConcurrentHashMap map);

}
