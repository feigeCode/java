package com.feige.test;

import com.feige.jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Objects;

public class QueryTableTest {
    public static void main(String[] args) {
        String sql = "select * from student";
        sql = "select * from course";
        sql = "select distinct class1 from student";
        sql = "select * from course where ctime>?";
        sql = "select sno,sname,sdate from student where year(sdate)=?";
        sql = "select sno,sname,sdate from student where year(sdate)=?";
        sql = "select sno,cno from score where grade1>=? and grade2>=? and grade3>=?";
        sql = "select sno,sname,class1 from student where sname like ?";
        sql = "select * from student where class1 like ? and sex=?";
        sql = "select sno,cno from score where grade1 is null or grade2 is null or grade3 is null";
        sql = "select sum(grade1) sum from score where sno=?";
        sql = "select count(distinct class1) from student";
        sql = "select sno,avg(grade1) avgGrade1,avg(grade2) avgGrade2,avg(grade3) avgGrade3 from score group by sno having count(*)>=3";
        sql = "select student.sno,sname,cname from student,course,score where score.sno=student.sno and score.cno=course.cno and sname=?";
        sql = "select * from student where class1=(select class1 from student where sname=?)";
        sql = "select s1.* from student s1,student s2 where s1.class1=s2.class1 and s2.sname=?";
        sql = "select * from course where ctime>(select ctime from course where cname=?)";
        sql = "select c1.* from course c1,course c2 where c2.cname=? and c1.ctime>c2.ctime";
        sql = "select student.sno,sname from student,score where student.sno=score.sno and score.cno=?";
        sql = "select * from score where sno not in (select sno from score where cno=? or cno=?)";
        queryTableTest(sql,"K001","M001");
    }
    public static void queryTableTest(String sql,Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            PreparedStatement psm = Objects.requireNonNull(connection).prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                psm.setObject(i+1,args[i]);
            }
            ResultSet rs = psm.executeQuery();
            ResultSetMetaData psmd = rs.getMetaData();
            int columnCount = psmd.getColumnCount();
            boolean b = true;
            while(rs.next()){
                if(b){
                    for (int i = 0; i < columnCount; i++) {
                        String columnLabel = psmd.getColumnLabel(i + 1);
                        System.out.print(columnLabel + "    ");
                    }
                    System.out.println();
                }
                b = false;
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i+1);
                    System.out.print(columnValue + "  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

