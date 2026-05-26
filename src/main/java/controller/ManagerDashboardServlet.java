package controller;

import dao.StatsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/managerdashboard")
public class ManagerDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        StatsDAO dao = new StatsDAO();

        request.setAttribute("stats", dao.getStats());

        request.getRequestDispatcher("/manager/dashboard.jsp")
               .forward(request, response);
    }
}