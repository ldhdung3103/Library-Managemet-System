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
}