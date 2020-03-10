package com.feige.web.servlet;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Properties;

//连接数据库
public class JdbcDemo1 {
    public static void main(String[] args) {
        getConnection();
    }
    /*
    把四个基本配置信息放在配置文件中,jdbc.properties
    url=jdbc:mysql://49.235.158.110:3306/2018143114?useUnicode=true&characterEncoding=UTF-8
    user=root
    password=Hufei169357@
    driverClass=com.mysql.jdbc.Driver
     */
    public static void getConnection(){
        //1.读取配置文件，配置文件放在src目录下
        InputStream is = JdbcDemo1.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        try {
            //4个基本配置信息
            pros.load(Objects.requireNonNull(is));
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");
            //2.加载驱动
            Class.forName(driverClass);
            //3.获取连接
            Connection conn = DriverManager.getConnection(url,user,password);
//            System.out.println(conn);
            //4.预编译sql
            String sql = "insert into student(sno,sname,sex,class,sdate,tel) values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"0755");
            ps.setString(2,"胡飞");
            ps.setString(3,"男");
            ps.setString(4,"软工31");
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            java.util.Date date = sdf.parse("2000-04-10");
            ps.setDate(5,new Date(date.getTime()));
            ps.setString(6,"15368760414");
            //5.执行操作
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
