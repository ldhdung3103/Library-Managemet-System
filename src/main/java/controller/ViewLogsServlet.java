<<<<<<< HEAD
package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/viewlogs")
public class ViewLogsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath() + "/frontend/manager/logs.html"
        );
    }
=======
package controller;

import dao.LogDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/viewlogs")
public class ViewLogsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        LogDAO dao = new LogDAO();

        request.setAttribute("logs", dao.getAllLogs());

        request.getRequestDispatcher("manager/logs.jsp")
               .forward(request, response);
    }
>>>>>>> 41085e156962748b988643765508fbf3d6064184
}