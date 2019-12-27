package com.cesar.core.test.service;

import com.cesar.core.test.entity.PublicImpProjectList;

import java.util.List;

public interface PublicImpProjectListService {

    /**
     * 公用-重点项目选择
     * Created by Cesar.Yuan
     */

    List<PublicImpProjectList> getPublicImpProjectList(String yearCode, String monthCode, String areaCode);

    String say();


}