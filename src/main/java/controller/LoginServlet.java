package controller;

import dao.UserDAO;
import model.User;
import utils.FrontendPaths;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

<<<<<<< Updated upstream
            if(user.getRole().equals("student")){
                response.sendRedirect(request.getContextPath() + "/student/dashboard.jsp");
            } else if(user.getRole().equals("librarian")){
                response.sendRedirect(request.getContextPath() + "/librarian/dashboard.jsp");
            } else {
<<<<<<< HEAD
                response.sendRedirect("manager/dashboard.jsp");
=======
            switch (user.getRole()) {
                case "student" ->
                        response.sendRedirect(FrontendPaths.studentDashboard(request));
                case "librarian" ->
                        response.sendRedirect(FrontendPaths.librarianDashboard(request));
                case "manager" ->
                        response.sendRedirect(FrontendPaths.managerDashboard(request));
                default ->
                        response.sendRedirect(FrontendPaths.login(request) + "?error=role");
>>>>>>> Stashed changes
=======
                response.sendRedirect(request.getContextPath() + "/managerdashboard");
>>>>>>> 41085e156962748b988643765508fbf3d6064184
            }
        } else {
            response.sendRedirect(FrontendPaths.login(request) + "?error=1");
        }
    }
}
