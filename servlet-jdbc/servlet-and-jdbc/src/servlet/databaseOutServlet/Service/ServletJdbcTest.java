package servlet.databaseOutServlet.Service;

import servlet.databaseOutServlet.utils.JdbcUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Objects;

/**
 * 把MySQL jar包放在Tomcat的libs目录下
 *
 */
@WebServlet("/output")
public class ServletJdbcTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sql = req.getParameter("sql");
        ResultSet rst = null;
        PreparedStatement pst = null;
        Connection conn;
        if("飞哥".equals(username) && "feige".equals(password)){
            conn = JdbcUtils.getConnection();
            try {
                pst = Objects.requireNonNull(conn).prepareStatement(sql);
                rst = pst.executeQuery();
                ResultSetMetaData rsm = rst.getMetaData();
                int count = rsm.getColumnCount();
                boolean b = true;
                resp.setHeader("Content-Type", "text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                while (rst.next()){
                    if(b){
                        for (int i = 0; i < count; i++) {
                            String columnLabel = rsm.getColumnLabel(i+1);
                            out.write("<p style='display:inline;font-size:20px;color:gold;'>"+columnLabel+"&nbsp;&nbsp;&nbsp;</p>");
                        }
                    }
                    out.println("<br>");
                    b = false;
                    for (int i = 0; i < count; i++) {
                        Object columnValue = rst.getObject(i+1);
                        out.print("<p style='display:inline;font-size:20px;color:red;'>"+columnValue+"&nbsp;&nbsp;&nbsp;</p>");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                JdbcUtils.closeConnection(conn,pst,rst);
            }
        }
    }
}
