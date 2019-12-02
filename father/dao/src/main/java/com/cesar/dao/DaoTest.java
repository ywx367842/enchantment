package com.cesar.dao;

import com.cesar.entity.EntityTest;

/**
 * @ClassName EntityTest
 * @Description: TODO
 * @Author movit
 * @Date 2019/12/2
 * @Version V1.0
 **/
public class DaoTest {
    public String showEntity(){
        EntityTest entityTest = new EntityTest();
        return entityTest.showEntity()+"我是dao!";
    }
}
