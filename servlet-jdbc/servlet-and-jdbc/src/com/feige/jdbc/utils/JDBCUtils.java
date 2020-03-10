package com.feige.jdbc.utils;


import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class JDBCUtils {
    /*
    在项目的主目录创建一个包，把数据库的jar包复制在你创建的目录里，光标放在你创建的目录上，右键-->Add as Library
    把四个基本配置信息放在配置文件中,jdbc.properties
    url=jdbc:mysql://49.235.158.110:3306/2018143114?useUnicode=true&characterEncoding=UTF-8
    user=root
    password=Hufei169357@
    driverClass=com.mysql.jdbc.Driver
     */
    public static void main(String[] args) {
        System.out.println(getConnection());
    }
    public static Connection getConnection(){
        //加载配置文件，读取4个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties ps = new Properties();
        try {
            ps.load(Objects.requireNonNull(is));

        //4个基本配置信息
        String url = ps.getProperty("url");
        String user = ps.getProperty("user");
        String password = ps.getProperty("password");
        String driverClass = ps.getProperty("driverClass");
        //2.加载驱动
        Class.forName(driverClass);
        //3.获取连接
        Connection conn;
        conn = DriverManager.getConnection(url,user,password);
        return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //关闭连接
    public static void closeConnection(Connection connection, PreparedStatement ps){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
