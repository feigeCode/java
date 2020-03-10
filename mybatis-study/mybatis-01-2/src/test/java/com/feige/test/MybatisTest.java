package com.feige.test;

import com.feige.mapper.StudentMapper;
import com.feige.mapper.TeacherMapper;
import com.feige.pojo.Student;
import com.feige.pojo.Teacher;
import com.feige.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

public class MybatisTest {
    static Logger logger = Logger.getLogger(MybatisTest.class);
    @Test
    public void mybatisTest1(){
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
    @Test
    public void mybatisTest2(){
        logger.info("info：进入limitUser方法");
        logger.debug("debug：进入limitUser方法");
        logger.error("error: 进入limitUser方法");
        SqlSession sqlSessioon = MybatisUtils.getSqlSessioon();
        TeacherMapper mapper = sqlSessioon.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher();
        System.out.println(teacher);
        sqlSessioon.close();
    }
}
