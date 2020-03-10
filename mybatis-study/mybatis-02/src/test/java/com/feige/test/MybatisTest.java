package com.feige.test;

import com.feige.dao.UserMapper;
import com.feige.pojo.User;
import com.feige.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


public class MybatisTest {
    @Test
    public void testSelectUserById() {
        SqlSession session = MybatisUtils.getSqlSessioon();
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<User> user = mapper.getAllUser();
        for (User user1 : user) {
            System.out.println(user1);
        }
        session.close();
    }
}
