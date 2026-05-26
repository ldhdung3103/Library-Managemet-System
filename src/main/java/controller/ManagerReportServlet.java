package controller;

import dao.StatsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/managerreport")
public class ManagerReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        StatsDAO dao = new StatsDAO();

        request.setAttribute("stats", dao.getStats());

        request.getRequestDispatcher("manager/report.jsp")
               .forward(request, response);
    }
}