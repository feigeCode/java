package com.feige.web.servlet;

import javax.servlet.*;
import java.io.IOException;
/*
<!--配置servlet-->
    <servlet>
        <servlet-name>demo1</servlet-name>
        <!--类路径名-->
        <servlet-class>ServletDemo</servlet-class>
    </servlet>
    <servlet-mapping>
    <!--路由名-->
        <servlet-name>demo1</servlet-name>
        <url-pattern>/demo1</url-pattern>
    </servlet-mapping>
 */
public class ServletDemo implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    //提供服务的方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
