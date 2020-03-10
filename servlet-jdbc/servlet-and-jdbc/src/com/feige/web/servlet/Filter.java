package com.feige.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/demo4")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 用于拦截用户的请求，如果和当前过滤器的拦截路径匹配，该方法会被调用
        PrintWriter out = resp.getWriter();
        out.write("Hello FilterTest");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
