package com.feige.mybatisTest;

import com.feige.dao.UserMapper;
import com.feige.pojo.User;
import com.feige.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    static Logger logger = Logger.getLogger(MybatisTest.class);

    @Test
    public void mybatisTest1(){
        SqlSession sqlSessioon = MybatisUtils.getSqlSessioon();
        UserMapper mapper = sqlSessioon.getMapper(UserMapper.class);
        List<User> allUser = mapper.getAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
        sqlSessioon.close();

    }
    @Test
    public void mybatisTest2(){
        SqlSession sqlSessioon = MybatisUtils.getSqlSessioon();
        UserMapper mapper = sqlSessioon.getMapper(UserMapper.class);
        User userById = mapper.getUserById(2018143114);
        System.out.println(userById);
        sqlSessioon.close();

    }
    @Test
    public void mybatisTest3() {
        SqlSession sqlSessioon = MybatisUtils.getSqlSessioon();
        UserMapper mapper = sqlSessioon.getMapper(UserMapper.class);
        //使用万能map传参
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pwd",2018143114);
        map.put("id",2018143114);

        int i = mapper.updateUserById(map);
        if (i > 0) {
            System.out.println("修改成功！"+i);
        }
        //增删改需要提交事务
        sqlSessioon.commit();

        sqlSessioon.close();

    }
    @Test
    public void mybatisTest4(){
        SqlSession sqlSessioon = MybatisUtils.getSqlSessioon();
        UserMapper mapper = sqlSessioon.getMapper(UserMapper.class);
        int i = mapper.deleteUserById(2018143117);
        if (i > 0) {
            System.out.println("删除成功！"+i);
        }
        sqlSessioon.commit();
        sqlSessioon.close();

    }
    @Test
    public void mybatisTest5(){
        SqlSession sqlSessioon = MybatisUtils.getSqlSessioon();
        UserMapper mapper = sqlSessioon.getMapper(UserMapper.class);
        User user = new User(2018143117, "杨飞", "2018143117");
        int i = mapper.insertUser(user);
        if (i > 0) {
            System.out.println("插入成功！"+i);
        }
        sqlSessioon.commit();
        sqlSessioon.close();

    }
    //模糊查询
    @Test
    public void mybatisTest6(){
        SqlSession session = MybatisUtils.getSqlSessioon();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username","%飞%");
        List<User> user = mapper.getUser(map);
        for (User user1 : user) {
            System.out.println(user1);
        }
        session.close();
    }
    @Test
    public void mybatisTest7(){
        logger.info("info：进入limitUser方法");
        logger.debug("debug：进入limitUser方法");
        logger.error("error: 进入limitUser方法");
        SqlSession session = MybatisUtils.getSqlSessioon();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> user = mapper.limitUser();
        for (User user1 : user) {
            System.out.println(user1);
        }
        session.close();
    }
}
