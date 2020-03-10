package servlet.databaseOutServlet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtils {
    public static Connection getConnection(){
        //四个基本配置信息
        String url = "jdbc:mysql://49.235.158.110:3306/2018143114?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String password = "Hufei169357@";
        String driver = "com.mysql.jdbc.Driver";
        Connection conn = null;
        try{
            //加载驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            return conn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConnection(Connection conn, PreparedStatement pst){
        try{
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(pst != null){
                pst.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rst){
        try{
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(pst != null){
                pst.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(rst != null){
                rst.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
