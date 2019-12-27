package com.cesar.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.cesar.core.user.mapper.UserMapper;
import com.cesar.core.user.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @ClassName SimpleTest
 * @Description: TODO
 * @Author movit
 * @Date 2019/9/29
 * @Version V1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5, users.size());
        users.forEach(System.out::println);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("向中");
        user.setAge(27);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);
        System.out.println("影响记录数：" + insert);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    @Test
    public void selectBatchIds() {
        List<Long> ldList = Arrays.asList(1094592041087729666L, 1088248166370832385L, 1182550951623966722L);
        List<User> users = userMapper.selectBatchIds(ldList);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        //key为tablefiled名称
        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("name","王天风");
        columnMap.put("age", 27);

        //where name = '王天风' and age=30
        List<User> users = userMapper.selectByMap(columnMap);
        users.forEach(System.out::println);
    }


    //条件构造器

    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //也可用工具类生成条件构造器
        //QueryWrapper<User> query = Wrappers.<User>query();

        //name like '%雨%' and age<40
        /*queryWrapper.like("name","雨").lt("age",40);
         */
        //name like '%雨%' and age between 20 and 40 and email is not null
        /*queryWrapper.like("name","雨")
                .between("age",20,40)
                .isNotNull("email");*/

        //name like '王%’ or age >= 25 order by age desc,id asc
        /*queryWrapper.likeRight("name","王")
                .or().ge("age",25)
                .orderByDesc("age")
                .orderByAsc("id");*/

        //date_format(create_time,'%Y-%m-%d') and manager_id in
        //(select id from user where name like '王%')
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-10-11")
//                .apply("manager_id in (select id from t_user where name like '王%')");
                .inSql("manager_id", "select id from t_user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //name like '王%' and (age<40 or email is not null)
        queryWrapper.likeRight("name", "王")
                .and(wq -> wq.lt("age", 40).or().isNotNull("email"));

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //name like '王%' or (age<40 and age>20 and email is not null)
        queryWrapper.likeRight("name", "王")
                .or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //(年龄小于40或邮箱不为空)并且姓名为'王'姓
        //（age<40 or email is not null) and name like '王%'
//       注意点: or 优先级小于 and
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //年龄为30、31、34、35
        //age in(30,31,34,35)
        List<Integer> ages = Arrays.asList(30, 31, 34, 35);
        queryWrapper.in("age", ages);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //limit 1
        queryWrapper.last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 部分字段查询
     */
    @Test
    public void selectByWrapperSupper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //name like '%雨%' and age<40
        queryWrapper
//                .select("id","name")
                .select(User.class, info -> !info.getColumn().equals("create_time") &&
                        !info.getColumn().equals("manager_id"))
                .like("name", "雨").lt("age", 40);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperEntity() {
        User whereUser = new User();
        whereUser.setName("刘红雨");
        whereUser.setAge(32);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);
        //name like '%雨%' and age<40
//        queryWrapper

//                .like("name","雨").lt("age",40);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperAllEq() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", null);
        queryWrapper.allEq(map);
        //SELECT id,name,age,email,manager_id,create_time FROM t_user WHERE name = ? AND age IS NULL
        queryWrapper.allEq(map, false);
        //SELECT id,name,age,email,manager_id,create_time FROM t_user WHERE name = ?
        List<User> users = userMapper.selectList(queryWrapper);
//        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperAllEqFunc() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", null);

        queryWrapper.allEq((k, v) -> !k.equals("name"), map);
        //SELECT id,name,age,email,manager_id,create_time FROM t_user WHERE age IS NULL

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void selectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("name", "雨")
                .between("age", 20, 40);

        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void selectMaps2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄
        //并且只取年龄总和小于500的组。
//        select avg(age) avg_age,min(age) min_age,max(age) max_age
//        from user
//        group by manager_id
//        having sum(age) < 500
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("manager_id")
                .having("sum(age) < {0}", 500);

        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectMapsCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("name", "雨")
                .lt("age", 40);

        Integer integer = userMapper.selectCount(queryWrapper);
        System.out.println(integer);
    }

    @Test
    public void selectByWrapperOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("name", "刘红雨")
                .lt("age", 40);

        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }


    @Test
    public void selectLambda() {

        //创建Lambda的三种方式
//        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();

        lambdaQuery.like(User::getName, "雨")
                .lt(User::getAge, 40);
        //where name like '%雨%' and age <40

        List<User> users = userMapper.selectList(lambdaQuery);
        users.forEach(System.out::println);
    }

    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();

        lambdaQuery.likeRight(User::getName, "王")
                .and(lqw -> lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));

        List<User> users = userMapper.selectList(lambdaQuery);
        users.forEach(System.out::println);
    }

    @Test
    public void selectLambda3() {
        LambdaQueryChainWrapper<User> wrapper = new LambdaQueryChainWrapper<User>(userMapper)
                .like(User::getName, "雨").ge(User::getAge, 20);

        List<User> users = wrapper.list();
//        List<User> users = userMapper.selectList(lambdaQuery);
        users.forEach(System.out::println);
    }


    /**
     * 自定义sql
     */
    @Test
    public void selectBySql() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();

        lambdaQuery.likeRight(User::getName, "王")
                .and(lqw -> lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));

        List<User> users = userMapper.selectAll(lambdaQuery);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByMySql() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "刘红雨");
        map.put("age", 40);
        List<User> users = userMapper.selectMyList(map);
        users.forEach(System.out::println);
    }


    /**
     * 分页
     */
    @Test
    public void testPage() {
        QueryWrapper<User> queryWrapper = Wrappers.<User>query();

        Page<User> page = new Page<>(1, 2);

        queryWrapper.ge("age", 20);

        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);

        System.out.println("总页数:" + userIPage.getPages());
        System.out.println("总记录数:" + userIPage.getTotal());
        List<User> users = userIPage.getRecords();
        users.forEach(System.out::println);
    }


    @Test
    public void testPage2() {
        QueryWrapper<User> queryWrapper = Wrappers.<User>query();

        Page<User> page = new Page<>(1, 2, false);

        queryWrapper.ge("age", 20);

        IPage<Map<String, Object>> userIPage = userMapper.selectMapsPage(page, queryWrapper);

        System.out.println("总页数:" + userIPage.getPages());
        System.out.println("总记录数:" + userIPage.getTotal());
        List<Map<String, Object>> users = userIPage.getRecords();
        users.forEach(System.out::println);
    }

    @Test
    public void testPage3() {
        Page<User> page = new Page<>(1, 2);

        QueryWrapper<User> query = Wrappers.<User>query();
        query.like("name", "雨");
        IPage<User> userIPage = userMapper.selectUserPage(page, query);

        System.out.println("总页数:" + userIPage.getPages());
        System.out.println("总记录数:" + userIPage.getTotal());
        List<User> users = userIPage.getRecords();
        users.forEach(System.out::println);
    }

    @Test
    public void testPage4() {
        Page<User> page = new Page<>(1, 2);

        User user = new User();
        user.setName("刘红雨");
        user.setAge(40);
        IPage<User> userIPage = userMapper.selectUserPage2(page, user);

        System.out.println("总页数:" + userIPage.getPages());
        System.out.println("总记录数:" + userIPage.getTotal());
        List<User> users = userIPage.getRecords();
        users.forEach(System.out::println);
    }

    @Test
    public void testPage5() {
        Page<User> page = new Page<>(1, 2);

        Map<String, Object> map = new HashMap<>();
//        map.put("name","刘红雨");
        map.put("age", 40);
        IPage<User> userIPage = userMapper.selectUserPage3(page, map);

        System.out.println("总页数:" + userIPage.getPages());
        System.out.println("总记录数:" + userIPage.getTotal());
        List<User> users = userIPage.getRecords();
        users.forEach(System.out::println);
    }
}












