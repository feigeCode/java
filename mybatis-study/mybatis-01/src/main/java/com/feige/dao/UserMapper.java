package com.feige.dao;

import com.feige.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getAllUser();
    //通过一个id查询一个用户
    User getUserById(int id);
    //通过id修改一个用户
    int updateUserById(Map map);
    //通过id删除一个用户
    int deleteUserById(int id);
    //插入一条数据
    int insertUser(User user);
    //like模糊查询
    List<User> getUser(Map map);
    //实现分页
    List<User> limitUser();

}
