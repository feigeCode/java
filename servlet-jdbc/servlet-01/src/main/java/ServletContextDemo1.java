import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/feige1")
public class ServletContextDemo1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //获取多个值，返回一个数组
        String[] hobbys = req.getParameterValues("hobbys");
        ServletContext context = this.getServletContext();
        //String username = "飞哥feige";
        context.setAttribute("username",username);
        context.setAttribute("password",password);
        context.setAttribute("hobbys",hobbys);
        //获取cookie
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            context.setAttribute("cookieName"+i,cookie.getName());
            context.setAttribute("cookieValue"+i,cookie.getValue());
        }
        //请求转发url不变307
        //重定向url改变302
        req.getRequestDispatcher("/feige2").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
