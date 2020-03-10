package com.feige.web.servlet;

import com.feige.test.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.feige.test.PreparedStateTest.queryForCustomers;

/**
 * Servlet路径配置
 * 可一个配置多个路径
 * *通配符
 * {"/user/login/*"}
 */
@WebServlet({"/demo4","/d"})
public class ServletDemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("用户名："+name+"密码"+password);
        if("飞哥".equals(name) || "hufei".equals(password)) {
            //重定向
            resp.sendRedirect("/web/success.html");
        }else {
            String sql = "select * from student";
            List<Customer> list = queryForCustomers(Customer.class,sql);
            assert list != null;
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>飞哥："+list.get(0)+"飞哥"+list.get(1)+"</h1>");
            out.println("<h1>用户名或密码错误</h1>");
            out.println("<h1>您输入的用户名："+name+"密码："+password+"</h1>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        username = new String(username.getBytes("iso8859-1"),"UTF-8");
        System.out.println("用户名："+username+"密码"+password);
        if("飞哥".equals(username) || "hufei".equals(password)) {
            //重定向
            resp.sendRedirect("/web/success.html");
        }else {
//            resp.setContentType("text/html;charset=UTF-8");
//            resp.setCharacterEncoding("utf-8");    //设置 HttpServletResponse使用utf-8编码
            resp.setHeader("Content-Type", "text/html;charset=utf-8");    //通知浏览器使用utf-8解码
            PrintWriter out = resp.getWriter();
            out.println("<h1>用户名或密码错误</h1>");
            out.println("<h1>您输入的用户名："+username+"密码："+password+"</h1>");
        }


    }
}
