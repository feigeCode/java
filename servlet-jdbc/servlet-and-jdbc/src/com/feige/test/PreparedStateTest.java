package com.feige.test;

import com.feige.jdbc.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreparedStateTest {
    public static void main(String[] args) {
//        String sql = "delete from student where sno=?";
//        updateDate(sql,"0756");
//        String sql = "update student set sno=? where sno=?";
//        updateDate(sql,"2018143114","0755");
//        String[] sno = new String[]{"0433","0496","0529","0531","0538","0591","0592"};
//        String[] name = new String[]{"张艳","李越","赵欣","张志国","于兰兰","王丽丽","王海强"};
//        for (int i = 0; i < sno.length ; i++) {
//            String sql = "update student set sname=? where sno=?";
//            updateDate(sql,name[i],sno[i]);
//        }
//        String sql = "insert into student(sno,sname,sex,class,sdate,tel) values(?,?,?,?,?,?)";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
//        java.util.Date date = null;
//        try {
//            date = sdf.parse("2000-02-10");
//            updateDate(sql,"2018143115","晋利","男","软工31",new Date(date.getTime()),"14769657343");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String sql = "select * from student where sno=?";
//            System.out.println(queryForCustomer(Customer.class,sql,"2018143114"));
        //当表名是sql关键字是表名加引号，如'order'
        String sql = "select * from student where sno=?";
        List<Customer> list = queryForCustomers(Customer.class,sql,"2018143115");
        assert list != null;
        list.forEach(System.out::println);
    }
    //Statement不能解决sql注入问题,PreparedStatement可以解决sql注入问题
    //PreparedStatement操作blob数据，而Statement不能
    //
    public static void updateDate(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement psm = null;
        try {
            //获取连接
            connection = JDBCUtils.getConnection();
            //预编译sql
            psm = Objects.requireNonNull(connection).prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                psm.setObject(i+1,args[i]);
            }
            //执行操作
            psm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            JDBCUtils.closeConnection(connection,psm);
        }

    }

    /**
     * JDBC（Java Database connectively）连接MYSQL数据库进行查询操作，
     * ORM（对象，关系，映射）思想编程，
     * 数据库中的一张表对应java中的一个类，
     * 表中的一个字段代表类中的一个属性，
     * 表中的一条数据代表一个对象
     * @param sql sql语句
     * @param args 填充占位符
     * @return 返回一条数据 [2018143114,胡飞,男,软工31,2000-04-10,15368760414]
     */
    //泛型方法,只能查询同一张表
    public static <T> T queryForCustomer(Class<T> clazz,String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            //获取连接
            connection = JDBCUtils.getConnection();
            //预编译sql
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            //执行操作，返回结果集
            rs = preparedStatement.executeQuery();
            //把结果集转化为元数据
            ResultSetMetaData rsmd = preparedStatement.getMetaData();
            //得到表的字段数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()){
                //创建一个对象
                //Customer customer = new Customer();
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //得到对应字段的名字和值
                    Object columnValue = rs.getObject(i+1);
                    //获取列的别名getColumnLabel(),用类属性名来命名数据库列名的别名
                    //获取列的名字getColumnName()
                    String columnName = rsmd.getColumnName(i+1);
                    //给customer对象指定columnName属性，属性值为columnValue，利用反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection,preparedStatement,rs);
        }
        return null;
    }
    //针对不表的查询
    public static <T> List<T> queryForCustomers(Class<T> clazz, String sql, Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            //获取连接
            connection = JDBCUtils.getConnection();
            //预编译sql
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            //执行操作，返回结果集
            rs = preparedStatement.executeQuery();
            //把结果集转化为元数据
            ResultSetMetaData rsmd = preparedStatement.getMetaData();
            //得到表的字段数
            int columnCount = rsmd.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()){
                //创建一个对象
                //Customer customer = new Customer();
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //得到对应字段的名字和值
                    Object columnValue = rs.getObject(i+1);
                    //获取列的别名getColumnLabel(),用类属性名来命名数据库列名的别名
                    //获取列的名字getColumnName()
                    String columnName = rsmd.getColumnName(i+1);
                    //给customer对象指定columnName属性，属性值为columnValue，利用反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection,preparedStatement,rs);
        }
        return null;
    }
}
