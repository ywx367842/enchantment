package com.cesar.core.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cesar.core.test.mapper.PublicImpProjectListDao;
import com.cesar.core.test.entity.PublicImpProjectList;
import com.cesar.core.test.service.PublicImpProjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PublicImpProjectListServiceImpl extends ServiceImpl<PublicImpProjectListDao, PublicImpProjectList> implements PublicImpProjectListService {

    /**
     * 公用-重点项目选择
     * Created by Cesar.Yuan
     */
    @Autowired
    private PublicImpProjectListDao publicImpProjectListDao;

    @Override
    public String say(){
        return "hello";
    }

    /*判断json返回类型*/
    @Override
    public List<PublicImpProjectList> getPublicImpProjectList(String yearCode, String monthCode, String areaCode) {
        ConcurrentHashMap map = new ConcurrentHashMap();

        if (yearCode != null && yearCode != "") {
            map.put("yearCode", yearCode);
        }
        if (monthCode != null && monthCode != "") {
            map.put("monthCode", monthCode);
        }
        if (areaCode != null && areaCode != "") {
            map.put("areaCode", areaCode);
        }


        return publicImpProjectListDao.getPublicImpProjectList(map);
    }
}

