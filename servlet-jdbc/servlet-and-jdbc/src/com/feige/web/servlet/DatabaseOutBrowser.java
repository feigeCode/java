package com.feige.web.servlet;

import com.feige.jdbc.utils.JDBCUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;


@WebServlet("/jdbc")
public class DatabaseOutBrowser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String method = req.getMethod();
        if("POST".equals(method)){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if("飞哥".equals(username) && "feige".equals(password)){
                String sql = "select * from student";
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rst = null;
                try {
                    String url = "jdbc:mysql://49.235.158.110:3306/2018143114?useUnicode=true&characterEncoding=UTF-8";
                    String user = "root";
                    String password1 = "Hufei169357@";
                    String driverClass = "com.mysql.jdbc.Driver";
                    //2.加载驱动
                    Class.forName(driverClass);
                    //3.获取连接
                    conn = DriverManager.getConnection(url,user,password1);
                    pst = Objects.requireNonNull(conn).prepareStatement(sql);
                    rst = pst.executeQuery();
                    ResultSetMetaData md = rst.getMetaData();
                    int columnCount = md.getColumnCount();
                    ArrayList<Object> list = new ArrayList<>();
                    while (rst.next()){
                        for (int i = 0; i < columnCount; i++) {
                            Object columnName = md.getColumnLabel(i+1);
                            Object columnValue = rst.getObject(i+1);
                            list.add(columnName);
                            list.add(columnValue);
                        }
                    }
                    resp.setHeader("Content-Type", "text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    for (int i = 0; i < list.size(); i++) {
                        out.println(list.get(i));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    JDBCUtils.closeConnection(conn,pst,rst);
                }
            }
        }
    }
}
