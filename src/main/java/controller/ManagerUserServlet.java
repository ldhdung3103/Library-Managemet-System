package controller;

import dao.UserDAO;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/manageusers")
public class ManagerUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO dao = new UserDAO();

        request.setAttribute("users", dao.getAllUsers());

        request.getRequestDispatcher("manager/manageusers.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");

        UserDAO dao = new UserDAO();

        if("add".equals(action)) {

            User user = new User();

            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setFullName(request.getParameter("fullName"));
            user.setEmail(request.getParameter("email"));
            user.setRole(request.getParameter("role"));

            dao.addUser(user);

        }
        else if("lock".equals(action)) {

            int userId =
                Integer.parseInt(request.getParameter("userId"));

            dao.updateStatus(userId, "suspended");
        }
        else if("unlock".equals(action)) {

            int userId =
                Integer.parseInt(request.getParameter("userId"));

            dao.updateStatus(userId, "active");
        }
        else if("delete".equals(action)) {

            int userId =
                Integer.parseInt(request.getParameter("userId"));

            dao.deleteUser(userId);
        }

        response.sendRedirect(
            request.getContextPath() + "/manageusers"
        );
    }
}