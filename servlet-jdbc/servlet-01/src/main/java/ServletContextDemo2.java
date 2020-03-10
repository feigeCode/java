import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/feige2")
public class ServletContextDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String username = (String) context.getAttribute("username");
        String password = (String) context.getAttribute("password");
        String[] hobbys = (String[]) context.getAttribute("hobbys");
        String cookieName0 = (String) context.getAttribute("cookieName0");
        String cookieValue0 = (String) context.getAttribute("cookieValue0");
        //resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Content-Type","text/html;charset=UTF-8");
        resp.getWriter().println("username="+username);
        resp.getWriter().println("password="+password);
//        for (String hobby:hobbys) {
//            resp.getWriter().print("hobby="+hobby);
//        }
        resp.getWriter().println(Arrays.toString(hobbys));
        resp.getWriter().println("cookieName0"+cookieName0);
        resp.getWriter().println("cookieValue0"+cookieValue0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
