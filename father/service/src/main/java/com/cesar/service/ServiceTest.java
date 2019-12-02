package com.cesar.service;

import com.cesar.dao.DaoTest;

/**
 * @ClassName EntityTest
 * @Description: TODO
 * @Author movit
 * @Date 2019/12/2
 * @Version V1.0
 **/
public class ServiceTest {
    public String showEntity(){
        DaoTest daoTest = new DaoTest();
        return daoTest.showEntity()+"我是service!";
    }
}
