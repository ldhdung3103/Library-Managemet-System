/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import model.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


/**
 *
 * @author MSI
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(username, password);

        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if(user.getRole().equals("student")){
                response.sendRedirect(request.getContextPath() + "/student/dashboard.jsp");
            } else if(user.getRole().equals("librarian")){
                response.sendRedirect(request.getContextPath() + "/librarian/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/managerdashboard");
            }

        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}