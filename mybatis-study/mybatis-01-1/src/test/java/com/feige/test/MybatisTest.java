package com.feige.test;

import com.feige.mapper.StudentMapper;
import com.feige.pojo.Student;
import com.feige.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

public class MybatisTest {
    static Logger logger = Logger.getLogger(MybatisTest.class);
    @Test
    public void mybatistest(){
        logger.info("info：进入limitUser方法");
        logger.debug("debug：进入limitUser方法");
        logger.error("error: 进入limitUser方法");
        SqlSession sqlSessioon = MybatisUtils.getSqlSessioon();
        StudentMapper mapper = sqlSessioon.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSessioon.close();
    }
}
