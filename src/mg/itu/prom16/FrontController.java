package mg.itu.prom16;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrontController extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            out.println("URL:" +request.getRequestURL());
        }
    }


}
