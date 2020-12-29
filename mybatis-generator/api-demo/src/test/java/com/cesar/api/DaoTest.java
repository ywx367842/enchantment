package com.cesar.api;


import com.cesar.test.entity.Clause;
import com.cesar.test.mapper.ClauseMapper;
import com.cesar.test.service.IClauseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@WebAppConfiguration
public class DaoTest {

    //    =====================daotest=====================
    @Autowired
    private ClauseMapper clauseMapper;

    @Test
    public void testClause() {
        List<Clause> clauses = clauseMapper.selectList(null);
        clauses.forEach(System.out::println);
    }

    //    =================servicetest====================
    @Autowired
//    @Qualifier("clauseServiceImpl")
    private IClauseService iClauseService;

    @Test
    public void testIClauseService() {
        List<Clause> list = iClauseService.list();
        list.forEach(System.out::println);
    }

}
